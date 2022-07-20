import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        //Fazer uma conexão HTTP e buscar os os top 250 filmes
        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();
        //Extrair só os dados que interessam(título, poster, classificação)
        JsonParser parser = new JsonParser();
        List<Map<String,String>> listaDeFilmes = parser.parse(body);
        //Exibir e manipular os dados
        listaDeFilmes.forEach(filme -> {
                    System.out.println(filme.get("title"));
                    System.out.println(filme.get("image"));
                    System.out.println(filme.get("imDbRating"));
                    System.out.println();
                });

    }
}
