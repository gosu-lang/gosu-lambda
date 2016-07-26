package org.gosu_lang.demo;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambdaClient;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.google.gson.Gson;
import gw.util.GosuExceptionUtil;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class SpaceMarineClient
{
  public static void main(String... args) {
    BasicAWSCredentials credentials = new BasicAWSCredentials( args[0], args[1] );
    AWSLambdaClient lambdaClient = new AWSLambdaClient( credentials );
    Region region = Region.getRegion( Regions.fromName( Regions.US_WEST_2.getName() ) );
    lambdaClient.setRegion(region);

    try {
      InvokeRequest invokeRequest = new InvokeRequest();
      invokeRequest.setFunctionName("purgeItInFlame");
      invokeRequest.setPayload("10");
      System.out.println(byteBufferToString(
        lambdaClient.invoke(invokeRequest).getPayload(),
        Charset.forName( "UTF-8")));
    } catch (Exception e) {
      GosuExceptionUtil.forceThrow( e );
    }
  }

  public static String byteBufferToString( ByteBuffer buffer, Charset charset) {
    byte[] bytes;
    if (buffer.hasArray()) {
      bytes = buffer.array();
    } else {
      bytes = new byte[buffer.remaining()];
      buffer.get(bytes);
    }
    Gson gson = new Gson();
    String json = new String( bytes, charset );
    System.out.printf( json );
    return gson.fromJson( json, String.class);
  }
}
