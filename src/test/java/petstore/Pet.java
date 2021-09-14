// 1- Pacote
package petstore;


// 2- Bibliotecas


import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.apache.groovy.json.internal.Chr.contains;
import static org.hamcrest.CoreMatchers.is;

// 3- Classe
public class Pet {
        //3.1 - Atributos
        String uri = "https://petstore.swagger.io/v2/pet";


        //3.2 - Métodos e Funções
        public String lerJson(String caminhoJson) throws IOException {

                return new String(Files.readAllBytes(Paths.get(caminhoJson)));

        }
        // Incluir - Create - Post
        @Test (priority = 1)
        public void incluirPet() throws IOException {
                String jsonBody = lerJson("db/pet1.json");

                // Sintaxe Gherkin
                //Dado - Quando - Então

                given()
                        .contentType("application/json")
                        .log().all()
                        .body(jsonBody)

                .when()
                        .post(uri)

                .then()
                        .log().all()
                        .statusCode(200)
                        .body("name", is("Valentina"))
                        .body("status", is("available"))

                ;
        }

        @Test(priority = 2)
        public void consultarPet(){
                String petId = "19920412124";

                given()
                        .contentType("application/json")
                        .log().all()

                 .when()
                        .get(uri+"/"+petId)

                  .then()
                        .log().all()
                        .statusCode(200)
                        .body("name", is ("Valentina"))
                        .body("category.name", is ("dog"))
                        .body("status", is("available"));




        }

}
