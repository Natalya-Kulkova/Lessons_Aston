package Lesson17.api;

import Lesson17.models.Pet;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PetStoreApi {
    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private final RequestSpecification requestSpec;

    public PetStoreApi() {
        requestSpec = RestAssured.given()
                .baseUri(BASE_URL)
                .contentType("application/json");
    }

    public Response createPet(Pet pet) {
        return requestSpec
                .body(pet)
                .post("/pet");
    }

    public Response getPetById(Long petId) {
        return requestSpec
                .get("/pet/" + petId);
    }

    public Response updatePet(Pet pet) {
        return requestSpec
                .body(pet)
                .put("/pet");
    }

    public Response deletePet(Long petId) {
        return requestSpec
                .delete("/pet/" + petId);
    }

    public Response findPetsByStatus(String status) {
        return requestSpec
                .queryParam("status", status)
                .get("/pet/findByStatus");
    }
}