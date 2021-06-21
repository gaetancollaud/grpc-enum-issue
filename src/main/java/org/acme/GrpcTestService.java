package org.acme;

import io.smallrye.mutiny.Uni;
import org.acme.grpc.v1.MutinyTestServiceGrpc;
import org.acme.grpc.v1.TestEnum;
import org.acme.grpc.v1.TestRequest;
import org.acme.grpc.v1.TestResponse;

import javax.inject.Singleton;

@Singleton
public class GrpcTestService extends MutinyTestServiceGrpc.TestServiceImplBase {

  @Override
  public Uni<TestResponse> testMethodOne(TestRequest request) {
    final String message;
    switch (request.getTestMessage().getTestField()) {
      case BAR:
        message = "bar";
        break;
      case FOO:
        message = "foo";
        break;
      default:
        message = "unknown";
    }
    return Uni.createFrom().item(TestResponse.newBuilder().setText(message).build());
  }

  @Override
  public Uni<TestResponse> testMethodTwo(TestRequest request) {
    final String message;
    switch (request.getTestMessage().getTestFieldValue()) {
      case org.acme.grpc.v1.TestEnum.Type.BAR_VALUE:
        message = "bar";
        break;
      case TestEnum.Type.FOO_VALUE:
        message = "foo";
        break;
      default:
        message = "unknown";
    }
    return Uni.createFrom().item(TestResponse.newBuilder().setText(message).build());
  }
}
