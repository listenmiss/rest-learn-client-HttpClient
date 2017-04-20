/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest.client.learn.httpclient;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Administrator
 */
public class HttpClentTest {

    public static final String BASE_URL = "http://localhost:8080/rest-jersey-learn/webapi/";

    public static void saveBook() throws IOException {

        String result = "";

        String jsonStr = "  {\n"
                + "      \"isbn\": \"9787121177378\",\n"
                + "      \"publishTime\": \"2012-09-03\",\n"
                + "     \n"
                + "      \"bookName\": \"JSF2和RichFaces4使用指南\",\n"
                + "      \"publisher\": \"电子工业出版社\"\n"
                + "    }";

        StringEntity paramEntity = new StringEntity(jsonStr, "utf-8");
        paramEntity.setContentEncoding("UTF-8");
        paramEntity.setContentType("application/json");

        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(BASE_URL + "media/jackson");
            httpPost.setEntity(paramEntity);

            CloseableHttpResponse response = httpclient.execute(httpPost);
            HttpEntity entity2 = response.getEntity();
            if (response.getStatusLine().getStatusCode() == 200) {//如果状态码为200,就是正常返回
                result = EntityUtils.toString(response.getEntity());
            }
            System.out.println("----------------------------------------");
            System.out.println(result);
        } catch (IOException ex) {
            Logger.getLogger(HttpClentTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            httpclient.close();
        }

    }
    public static void saveBook2() throws IOException {
        String result = "";
       String bookName="listen的图书";
        String jsonStr = "  {\n"
                + "      \"isbn\": \"9787121177378\",\n"
                + "      \"publishTime\": \"2012-09-03\",\n"
                + "     \n"
                + "      \"bookName\": \"JSF2和RichFaces4使用指南\",\n"
                + "      \"publisher\": \"电子工业出版社\"\n"
                + "    }";

        StringEntity paramEntity = new StringEntity(jsonStr, "utf-8");
        paramEntity.setContentEncoding("UTF-8");
        paramEntity.setContentType("application/json");

        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(BASE_URL + "media/jackson/save/"+bookName);
            httpPost.setEntity(paramEntity);

            CloseableHttpResponse response = httpclient.execute(httpPost);
            HttpEntity entity2 = response.getEntity();
            if (response.getStatusLine().getStatusCode() == 200) {//如果状态码为200,就是正常返回
                result = EntityUtils.toString(response.getEntity());
            }
            System.out.println("----------------------------------------");
            System.out.println(result);
        } catch (IOException ex) {
            Logger.getLogger(HttpClentTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            httpclient.close();
        }

    }
    public static void updateBook() throws IOException {

        String result = "";

        String jsonStr = "    {\n"
                + "      \"isbn\": \"9787121177378\",\n"
                + "      \"publishTime\": \"2012-09-03\",\n"
                + "      \"bookId\": 1,\n"
                + "      \"bookName\": \"JSF2和RichFaces4使用指南\",\n"
                + "      \"publisher\": \"电子工业出版社\"\n"
                + "    }";

        StringEntity paramEntity = new StringEntity(jsonStr, "utf-8");
        paramEntity.setContentEncoding("UTF-8");
        paramEntity.setContentType("application/json");

        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPut httpPut = new HttpPut(BASE_URL + "media/jackson");
            httpPut.setEntity(paramEntity);

            CloseableHttpResponse response = httpclient.execute(httpPut);
            HttpEntity entity2 = response.getEntity();
            if (response.getStatusLine().getStatusCode() == 200) {//如果状态码为200,就是正常返回
                result = EntityUtils.toString(response.getEntity());
            }
            System.out.println("----------------------------------------");
            System.out.println(result);
        } catch (IOException ex) {
            Logger.getLogger(HttpClentTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            httpclient.close();
        }

    }

    public static void deleteBook() throws IOException {

        String result = "";
        String queryParams = "?bookId=" + 2;

        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpDelete httpDelete = new HttpDelete(BASE_URL + "media/jackson" + queryParams);

            CloseableHttpResponse response = httpclient.execute(httpDelete);
            HttpEntity entity2 = response.getEntity();

            System.out.println("----------------------------------------");
            System.out.println("ok");
        } catch (IOException ex) {
            Logger.getLogger(HttpClentTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            httpclient.close();
        }

    }

    public static void deleteBook2() throws IOException {

        String result = "";
        String queryParams = "?bookId=" + 1;

        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpDelete httpDelete = new HttpDelete(BASE_URL + "media/jackson/del/" + queryParams);

            CloseableHttpResponse response = httpclient.execute(httpDelete);
            HttpEntity entity2 = response.getEntity();
            if (response.getStatusLine().getStatusCode() == 200) {//如果状态码为200,就是正常返回
                result = EntityUtils.toString(response.getEntity());
            }

            System.out.println("----------------------------------------");
            System.out.println(result);
        } catch (IOException ex) {
            Logger.getLogger(HttpClentTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            httpclient.close();
        }

    }

    public static void getBooks() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet(BASE_URL + "media/jackson");

            System.out.println("Executing request " + httpget.getRequestLine());

            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
            String responseBody = httpclient.execute(httpget, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
        } catch (IOException ex) {
            Logger.getLogger(HttpClentTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            httpclient.close();
        }
    }

    public static void getBooks2() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String result = "";
        try {
            HttpGet httpget = new HttpGet(BASE_URL + "media/jackson");

            System.out.println("Executing request " + httpget.getRequestLine());

            // 通过请求对象获取响应对象
            HttpResponse response = httpclient.execute(httpget);
            // 判断网络连接状态码是否正常(0--200都数正常)
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity(), "utf-8");
            }

            System.out.println("----------------------------------------");
            System.out.println(result);
        } catch (IOException ex) {
            Logger.getLogger(HttpClentTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            httpclient.close();
        }
    }

    public static void main(String[] args) throws IOException {
// HttpClentTest.saveBook();
//HttpClentTest.saveBook2();
//  HttpClentTest.deleteBook();
//  HttpClentTest.deleteBook2();
//HttpClentTest.updateBook();
        HttpClentTest.getBooks();
//        HttpClentTest.getBooks2();

    }
}
