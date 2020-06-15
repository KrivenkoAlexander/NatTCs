import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static java.lang.Math.pow;


public class MainTest {
    String baseURL ="https://qa-quiz.natera.com";
    String  token ="YourTOKEN";

    @BeforeMethod
    public void addOneBaseTriangle (){
        Map<String,String> body = new HashMap<String, String>();
        body.put("separator",";");
        body.put("input","3;4;5");
        Response response =
                given()
                        .header("X-User",token)
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .body(body)
                        .post(baseURL+"/triangle")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .response();
    }

    @Test
    public void TestGet(){
        Response response =
                given()
                .header("X-User",token)
                .log().all()
                .when()
                .get(baseURL+"/triangle/all")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();
    }
    
    @Test
    public void TestGetInvalidToken(){
        String testToken ="";
        Response response =
                given()
                        .header("X-User",testToken)
                        .log().all()
                        .when()
                        .get(baseURL+"/triangle/all")
                        .then()
                        .log().all()
                        .statusCode(401)
                        .extract()
                        .response();
    }
    
    @Test
    public void TestGet404Code(){
        String elementID = " ";
        Response response =
                given()
                        .header("X-User",token)
                        .log().all()
                        .when()
                        .get(baseURL+"/triangle/"+elementID)
                        .then()
                        .log().all()
                        .statusCode(404)
                        .extract()
                        .response();
    }
    
    @Test
    public void TestHead404Code(){
        String elementID = " ";
        Response response =
                given()
                        .header("X-User",token)
                        .log().all()
                        .when()
                        .head(baseURL+"/triangle/"+elementID)
                        .then()
                        .log().all()
                        .statusCode(404)
                        .extract()
                        .response();
    }
    
    @Test
    public void TestPut404Code(){
        String elementID = " ";
        Response response =
                given()
                        .header("X-User",token)
                        .log().all()
                        .when()
                        .put(baseURL+"/triangle/"+elementID)
                        .then()
                        .log().all()
                        .statusCode(404)
                        .extract()
                        .response();
    }
    
    @Test
    public void TestPatch404Code(){
        String elementID = " ";
        Response response =
                given()
                        .header("X-User",token)
                        .log().all()
                        .when()
                        .patch(baseURL+"/triangle/"+elementID)
                        .then()
                        .log().all()
                        .statusCode(404)
                        .extract()
                        .response();
    }
    
    @Test
    public void TestPerimeter404Code(){
        String elementID = " ";
        Response response =
                given()
                        .header("X-User",token)
                        .log().all()
                        .when()
                        .get(baseURL+"/triangle/"+elementID+"/perimeter")
                        .then()
                        .log().all()
                        .statusCode(404)
                        .extract()
                        .response();
    }
    
    @Test
    public void TestArea404Code(){
        String elementID = " ";
        Response response =
                given()
                        .header("X-User",token)
                        .log().all()
                        .when()
                        .get(baseURL+"/triangle/"+elementID+"/area")
                        .then()
                        .log().all()
                        .statusCode(404)
                        .extract()
                        .response();
    }
    
    @Test
    public void TestDelete404Code(){
        String elementID = " ";
        Response response =
                given()
                        .header("X-User",token)
                        .log().all()
                        .when()
                        .delete(baseURL+"/triangle/"+elementID)
                        .then()
                        .log().all()
                        .statusCode(404)
                        .extract()
                        .response();
    }
    
    @Test
    public void TestMandatoryField(){
        Map<String,String> body = new HashMap<String, String>();
        body.put("input","3;4;5");
        Response response=
                given()
                        .header("X-User",token)
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .body(body)
                        .post(baseURL+"/triangle")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .response();
    }
    
    @DataProvider(name = "customSeparator")
    public Object[][] customSeparatorproviderMethod(){
        return new Object[][] { {""},
                                {"-"},
                                {":"},
                                {" "},
                                {"@"},
                                {"/"},
                                {"::"},
                                {"\\"},
                                {"_"}};
    }
    
    @Test (dataProvider ="customSeparator")
    public void TestCustomSeparator(String separator){
        Map<String,String> body = new HashMap<String, String>();
        body.put("separator",separator);
        body.put("input","34"+separator+"45"+separator+"66");
        Response response=
                given()
                        .header("X-User",token)
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .body(body)
                        .post(baseURL+"/triangle")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .response();
    }
    
    @Test
    public void TestNoInputField(){
        Map<String,String> body = new HashMap<String, String>();
        body.put("separator",":");
        Response response=
                given()
                        .header("X-User",token)
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .body(body)
                        .post(baseURL+"/triangle")
                        .then()
                        .log().all()
                        .statusCode(422)
                        .extract()
                        .response();
    }
    @Test
    public void TestEmptyBody(){
        Map<String,String> body = new HashMap<String, String>();
        body.put("","");
        body.put("","");
        Response response=
                given()
                        .header("X-User",token)
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .body(body)
                        .post(baseURL+"/triangle")
                        .then()
                        .log().all()
                        .statusCode(422)
                        .extract()
                        .response();
    }

    @Test
    public void TestTyposInJsonSchema(){
        Map<String,String> body = new HashMap<String, String>();
        body.put("separator",":");
        body.put("inputt","34:45:55");
        Response response=
                given()
                        .header("X-User",token)
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .body(body)
                        .post(baseURL+"/triangle")
                        .then()
                        .log().all()
                        .statusCode(422)
                        .extract()
                        .response();
    }
            
    @Test  
    public void TestAddTr () {
        Response responseGet =
                given()
                        .header("X-User",token)
                        .log().all()
                        .when()
                        .get(baseURL+"/triangle/all")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .response();
        
        List<String> lenTriangles = responseGet.getBody().jsonPath().getList("$");
        Map<String,String> body = new HashMap<String, String>();
        body.put("separator",";");
        body.put("input","3;4;5");
        Response response=
                given()
                        .header("X-User",token)
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .body(body)
                        .post(baseURL+"/triangle")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .response();
        
        Response responseGet2 =
                given()
                        .header("X-User",token)
                        .log().all()
                        .when()
                        .get(baseURL+"/triangle/all")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .response();
        List<String> lenTriangles2 = responseGet2.getBody().jsonPath().getList("$");
        Assert.assertEquals(lenTriangles.size()+1,lenTriangles2.size());
    }
    
    @DataProvider(name = "triangles")
    public Object[][] trianglesproviderMethod(){
        return new Object[][] { {"10","20","30"},
                                {"1000","2000","3000"},
                                {"3000","3000","3000"}};
    }
    
    @Test (dataProvider = "triangles")
    public void TestAddTrianglePositiveVal(String firstSide, String secondSide, String thirdSide) {
        
    /* Remove all triangles to be sure that the server doesn't contain anything */
        ClearTriangleList();
        Map<String,String> body = new HashMap<String, String>();
        body.put("separator",";");
        body.put("input",firstSide+";"+secondSide+";"+thirdSide);
        Response response =
                given()
                        .header("X-User",token)
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .body(body)
                        .post(baseURL+"/triangle")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .response();

        Response responseGet2 =
                given()
                        .header("X-User",token)
                        .log().all()
                        .when()
                        .get(baseURL+"/triangle/all")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .response();
        String resFirstSide = responseGet2.getBody().jsonPath().getString("firstSide[0]");
        String resSecondSide = responseGet2.getBody().jsonPath().getString("secondSide[0]");
        String resThirdSide = responseGet2.getBody().jsonPath().getString("thirdSide[0]");

        Assert.assertEquals(Float.valueOf(firstSide).toString(),resFirstSide);
        Assert.assertEquals(Float.valueOf(secondSide).toString(),resSecondSide);
        Assert.assertEquals(Float.valueOf(thirdSide).toString(),resThirdSide);

    }
    
    @DataProvider(name = "trianglesInvalid")
    public Object[][] trianglesInvalidProviderMethod(){
        return new Object[][] { 
                                {"0","0","0"},
                                {"","",""},
                                {" "," "," "},
                                {"-3000","3000","3000"},
                                {"3000","-3000","3000"},
                                {"3000","3000","-3000"},
                                {"a","50","50"},
                                {"50","b","50"},
                                {"50","50","c"},
                                {"!","50","50"},
                                {"50","#","50"},
                                {"50","50","$"}};
    }

    @Test (dataProvider = "trianglesInvalid")
    public void TestAddTrSidesInvalidVal (String firstSide, String secondSide, String thirdSide) {
        Map<String,String> body = new HashMap<String, String>();
        body.put("separator",";");
        body.put("input",firstSide+";"+secondSide+";"+thirdSide);
        Response response =
                given()
                        .header("X-User",token)
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .body(body)
                        .post(baseURL+"/triangle")
                        .then()
                        .log().all()
                        .statusCode(422)
                        .extract()
                        .response();
    }
    
    @Test
    public void TestGetTrByID(){
        
        String elementID = 
                given()
                        .header("X-User",token)
                        .log().all()
                        .when()
                        .get(baseURL+"/triangle/all")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .response().getBody().jsonPath().getString("id[0]");
                
        Response response =
                given()
                        .header("X-User",token)
                        .log().all()
                        .when()
                        .get(baseURL+"/triangle/"+elementID)
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .response();
        String resTrID = response.getBody().jsonPath().getString("id");
        Assert.assertEquals(resTrID,elementID,"Two ids  should be the same");
    }
    
    @Test
    public void TestTrPerimeter(){
        Response responseGetAll =
                given()
                        .header("X-User",token)
                        .log().all()
                        .when()
                        .get(baseURL+"/triangle/all")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .response();
        
        String elementID =responseGetAll.getBody().jsonPath().getString("id[0]");
        float firstSide = responseGetAll.getBody().jsonPath().getFloat("firstSide[0]");
        float secondSide = responseGetAll.getBody().jsonPath().getFloat("secondSide[0]");
        float thirdSide = responseGetAll.getBody().jsonPath().getFloat("thirdSide[0]");
        float perimeter = firstSide + secondSide + thirdSide; 
        
        Response response =
                given()
                        .header("X-User",token)
                        .log().all()
                        .when()
                        .get(baseURL+"/triangle/"+elementID+"/perimeter")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .response();
        float resPerimeter = response.getBody().jsonPath().getFloat("result");
        Assert.assertEquals(resPerimeter,perimeter, "Should be the same");
    }
    
    @Test
    public void TestArea(){
        Response responseGetAll =
                given()
                        .header("X-User",token)
                        .log().all()
                        .when()
                        .get(baseURL+"/triangle/all")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .response();

        String elementID =responseGetAll.getBody().jsonPath().getString("id[0]");
        float firstSide = responseGetAll.getBody().jsonPath().getFloat("firstSide[0]");
        float secondSide = responseGetAll.getBody().jsonPath().getFloat("secondSide[0]");
        float thirdSide = responseGetAll.getBody().jsonPath().getFloat("thirdSide[0]");
        float perimeter = firstSide + secondSide + thirdSide;
        float area =(float) pow(perimeter/2*(perimeter/2-firstSide)*(perimeter/2-secondSide)*(perimeter/2-thirdSide),0.5);

        Response response =
                given()
                        .header("X-User",token)
                          .log().all()
                        .when()
                        .get(baseURL+"/triangle/"+elementID+"/area")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .response();
        float resArea = response.getBody().jsonPath().getFloat("result");
        Assert.assertEquals(resArea,area, "Should be the same");
        
    }
    @Test
    public void TestDeletionOfTriangle(){
        Response responseGetBeforeDel =
                given()
                        .header("X-User",token)
                        .log().all()
                        .when()
                        .get(baseURL+"/triangle/all")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .response();
        List<String> listBeforeDel = responseGetBeforeDel.getBody().jsonPath().getList("id");
        String trId = listBeforeDel.get(0);
        
        Response responseDel=
                given()
                        .header("X-User",token)
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .delete(baseURL+"/triangle/"+trId)
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .response();
        
        Response responseGetAfterDel =
                given()
                        .header("X-User",token)
                        .log().all()
                        .when()
                        .get(baseURL+"/triangle/all")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .response();
        List<String> listAfterDel = responseGetAfterDel.getBody().jsonPath().getList("id");
        Assert.assertTrue(!listAfterDel.contains(trId));
        
    }
    
    @DataProvider(name = "PositiveValTriangles")
    public Object[][] PositiveValTriangles (){
        return new Object[][]{{3},{10}};
    }
    
    @Test (dataProvider ="PositiveValTriangles" )
    public void TestWithInBoundaries(int maxVal){
        /*Remove all triangles to be sure that the server doesn't contain anything*/
        ClearTriangleList();
        
        /*Start test*/
        int resTriangleListSize;
        for (int i = 1; i <=maxVal ; i++) {
            Map<String,String> body = new HashMap<String, String>();
            body.put("separator",";");
            body.put("input","3;4;5");
            Response responsePOST =
                given()
                        .header("X-User",token)
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .body(body)
                        .post(baseURL+"/triangle")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .response();
        }
        
        /*Get the result list of triangles*/
        Response responseGetAll =
                given()
                        .header("X-User",token)
                        .log().all()
                        .when()
                        .get(baseURL+"/triangle/all")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .response();

        resTriangleListSize =responseGetAll.getBody().jsonPath().getList("id").size();
        Assert.assertTrue(resTriangleListSize==maxVal);
    }
    
    @DataProvider(name = "invalidValTriangles")
    public Object[][] invalidValTriangles (){
        return new Object[][]{{0},{11}};
    }

    @Test (dataProvider ="invalidValTriangles" )
    public void TestOutOfBoundaries(int val){
        
        /* Remove all triangles to be sure that the server doesn't contain anything */
        ClearTriangleList();
        
        /* Start test */
        int resTriangleListSize;
        for (int i = 1; i <=val ; i++) {
            Map<String,String> body = new HashMap<String, String>();
            body.put("separator",";");
            body.put("input","3;4;5");
            if (i!=val) {
                Response responsePOST =
                        given()
                                .header("X-User",token)
                                .contentType(ContentType.JSON)
                                .log().all()
                                .when()
                                .body(body)
                                .post(baseURL+"/triangle")
                                .then()
                                .log().all()
                                .statusCode(200)
                                .extract()
                                .response();
            }else {
                Response responsePOST =
                        given()
                                .header("X-User",token)
                                .contentType(ContentType.JSON)
                                .log().all()
                                .when()
                                .body(body)
                                .post(baseURL+"/triangle")
                                .then()
                                .log().all()
                                .statusCode(422)
                                .extract()
                                .response();
            }
        }
        /* Get the result list of triangles */
        Response responseGetAll =
                given()
                        .header("X-User",token)
                        .log().all()
                        .when()
                        .get(baseURL+"/triangle/all")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .response();

        resTriangleListSize =responseGetAll.getBody().jsonPath().getList("id").size();
        Assert.assertTrue(resTriangleListSize<=val);
    }

    @AfterMethod
    public void removeTriangles(){
        ClearTriangleList();
    }

    private void ClearTriangleList() {
        Response responseGet =
                given()
                        .header("X-User",token)
                        .log().all()
                        .when()
                        .get(baseURL+"/triangle/all")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .response();
        List<String> TrList = responseGet.getBody().jsonPath().getList("id");
        for (String trId : TrList) {
            Response responseDel=
                    given()
                            .header("X-User",token)
                            .contentType(ContentType.JSON)
                            .log().all()
                            .when()
                            .delete(baseURL+"/triangle/"+trId)
                            .then()
                            .log().all()
                            .statusCode(200)
                            .extract()
                            .response();
        }
    }
}
