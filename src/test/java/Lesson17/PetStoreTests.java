package Lesson17;

import Lesson17.api.PetStoreApi;
import Lesson17.models.Category;
import Lesson17.models.Pet;
import Lesson17.models.PetTag;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PetStoreTests {
    private PetStoreApi petStoreApi;

    @BeforeEach
    void setUp() {
        petStoreApi = new PetStoreApi();
    }

    @Test
    @DisplayName("Создание нового питомца")
    void testCreatePet() {
        // Подготовка данных
        Category category = new Category(1L, "Dogs");
        PetTag[] tags = {new PetTag(1L, "friendly")};
        Pet pet = new Pet(
                12345L,
                category,
                "Doggie",
                new String[]{"https://example.com/dog.jpg"},
                tags,
                "available"
        );

        // Отправка запроса
        Response response = petStoreApi.createPet(pet);

        // Проверки
        assertEquals(200, response.getStatusCode(), "Неверный код ответа");
        Pet createdPet = response.as(Pet.class);
        assertEquals(pet.getName(), createdPet.getName(), "Имя питомца не совпадает");
        assertEquals(pet.getStatus(), createdPet.getStatus(), "Статус питомца не совпадает");
    }

    @Test
    @DisplayName("Получение питомца по ID")
    void testGetPetById() {
        // Создаем питомца
        Category category = new Category(1L, "Dogs");
        PetTag[] tags = {new PetTag(1L, "friendly")};
        Pet pet = new Pet(
                12346L,
                category,
                "Rex",
                new String[]{"https://example.com/rex.jpg"},
                tags,
                "available"
        );
        petStoreApi.createPet(pet);

        // Получаем питомца по ID
        Response response = petStoreApi.getPetById(pet.getId());

        // Проверки
        assertEquals(200, response.getStatusCode(), "Неверный код ответа");
        Pet fetchedPet = response.as(Pet.class);
        assertEquals(pet.getId(), fetchedPet.getId(), "ID питомца не совпадает");
        assertEquals(pet.getName(), fetchedPet.getName(), "Имя питомца не совпадает");
    }

    @Test
    @DisplayName("Обновление информации о питомце")
    void testUpdatePet() {
        // Создаем питомца
        Category category = new Category(1L, "Dogs");
        PetTag[] tags = {new PetTag(1L, "friendly")};
        Pet pet = new Pet(
                12347L,
                category,
                "Max",
                new String[]{"https://example.com/max.jpg"},
                tags,
                "available"
        );
        petStoreApi.createPet(pet);

        // Обновляем данные
        PetTag[] newTags = {new PetTag(1L, "friendly"), new PetTag(2L, "trained")};
        Pet updatedPet = new Pet(
                pet.getId(),
                category,
                "Updated Max",
                pet.getPhotoUrls(),
                newTags,
                "sold"
        );
        Response response = petStoreApi.updatePet(updatedPet);

        // Проверки
        assertEquals(200, response.getStatusCode(), "Неверный код ответа");
        Pet resultPet = response.as(Pet.class);
        assertEquals(updatedPet.getName(), resultPet.getName(), "Имя питомца не обновилось");
        assertEquals(updatedPet.getStatus(), resultPet.getStatus(), "Статус питомца не обновился");
    }

    @Test
    @DisplayName("Удаление питомца")
    void testDeletePet() {
        // Создаем питомца
        Category category = new Category(1L, "Dogs");
        PetTag[] tags = {new PetTag(1L, "temporary")};
        Pet pet = new Pet(
                12348L,
                category,
                "ToDelete",
                new String[]{"https://example.com/delete.jpg"},
                tags,
                "available"
        );
        petStoreApi.createPet(pet);

        // Удаляем питомца
        Response deleteResponse = petStoreApi.deletePet(pet.getId());
        assertEquals(200, deleteResponse.getStatusCode(), "Неверный код ответа при удалении");

        // Проверяем, что питомец удален
        Response getResponse = petStoreApi.getPetById(pet.getId());
        assertEquals(404, getResponse.getStatusCode(), "Питомец не был удален");
    }

    @Test
    @DisplayName("Поиск питомцев по статусу")
    void testFindPetsByStatus() {
        Response response = petStoreApi.findPetsByStatus("available");

        assertEquals(200, response.getStatusCode(), "Неверный код ответа");
        Pet[] pets = response.as(Pet[].class);
        assertTrue(pets.length > 0, "Список питомцев пуст");

        for (Pet pet : pets) {
            assertEquals("available", pet.getStatus(),
                    "Найден питомец с неверным статусом: " + pet.getStatus());
        }
    }
}