import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class Lesson8 {
    public static void main(String[] args) {
        try {
            // URL для запроса
            String url = "https://swapi.dev/api/people/";

            // Создание объекта URL
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // Установка метода запроса
            con.setRequestMethod("GET");

            // Получение кода ответа
            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Чтение ответа
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Преобразование ответа в JSON
            JSONObject jsonResponse = new JSONObject(response.toString());

            // Вывод информации о персонажах
            System.out.println("Информация о персонажах: " + jsonResponse.toString(2)); // Форматированный вывод

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
