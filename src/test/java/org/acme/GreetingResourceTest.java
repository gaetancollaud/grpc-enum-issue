package org.acme;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.quarkus.test.junit.QuarkusTest;
import org.acme.grpc.v1.MutinyTestServiceGrpc;
import org.acme.grpc.v1.TestEnum;
import org.acme.grpc.v1.TestMessage;
import org.acme.grpc.v1.TestRequest;
import org.acme.grpc.v1.TestResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class GreetingResourceTest {

  MutinyTestServiceGrpc.MutinyTestServiceStub stub;

  @BeforeEach
  void setup() {
    final ManagedChannel managedChannel =
        ManagedChannelBuilder.forAddress("localhost", 9000).usePlaintext().build();
    stub = MutinyTestServiceGrpc.newMutinyStub(managedChannel);
  }

  @Test
  public void testMethodOne() {

    final TestResponse response =
        stub.testMethodOne(
                TestRequest.newBuilder()
                    .setTestMessage(
                        TestMessage.newBuilder().setTestField(TestEnum.Type.BAR).build())
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
                    .setTestMessage(
                        TestMessage.newBuilder().setTestField(TestEnum.Type.BAR).build())
                    .build())
            .await()
            .indefinitely();

    assertThat(response.getText()).isEqualTo("bar");
  }
}
