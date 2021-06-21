package org.acme;

import io.quarkus.grpc.GrpcClient;
import io.quarkus.test.junit.QuarkusTest;
import org.acme.grpc.v1.MutinyTestServiceGrpc;
import org.acme.grpc.v1.TestEnum;
import org.acme.grpc.v1.TestMessage;
import org.acme.grpc.v1.TestRequest;
import org.acme.grpc.v1.TestResponse;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class GreetingResourceTest {

  @GrpcClient("test")
  MutinyTestServiceGrpc.MutinyTestServiceStub stub;

  @Test
  public void testMethodOne() {

    final TestResponse response =
        stub.testMethodOne(
                TestRequest.newBuilder()
                    .setTestMessage(TestMessage.newBuilder().setTestField(TestEnum.Type.BAR).build())
                    .build())
            .await()
            .indefinitely();

    assertThat(response.getText()).isEqualTo("bar");
  }

  @Test
  public void testMethodTwo() {
    final TestResponse response =
        stub.testMethodTwo(
                TestRequest.newBuilder()
                    .setTestMessage(TestMessage.newBuilder().setTestField(TestEnum.Type.BAR).build())
                    .build())
            .await()
            .indefinitely();

    assertThat(response.getText()).isEqualTo("bar");
  }
}
