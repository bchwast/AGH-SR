// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: calculator.proto

package gen;

public final class CalculatorServiceProto {
  private CalculatorServiceProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_calculator_ArithmeticOpArguments_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_calculator_ArithmeticOpArguments_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_calculator_ArithmeticOpResult_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_calculator_ArithmeticOpResult_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_calculator_PrimeTesterArguments_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_calculator_PrimeTesterArguments_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_calculator_PrimeTesterResult_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_calculator_PrimeTesterResult_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\020calculator.proto\022\ncalculator\"P\n\025Arithm" +
      "eticOpArguments\022)\n\006opType\030\001 \001(\0162\031.calcul" +
      "ator.OperationType\022\014\n\004args\030\002 \003(\001\"!\n\022Arit" +
      "hmeticOpResult\022\013\n\003res\030\001 \001(\001\"&\n\024PrimeTest" +
      "erArguments\022\016\n\006number\030\001 \001(\003\"$\n\021PrimeTest" +
      "erResult\022\017\n\007isPrime\030\001 \001(\010*=\n\rOperationTy" +
      "pe\022\007\n\003SUM\020\000\022\007\n\003AVG\020\001\022\010\n\004MULT\020\002\022\007\n\003MIN\020\003\022" +
      "\007\n\003MAX\020\0042\267\001\n\021CalculatorService\022P\n\tOperat" +
      "ion\022!.calculator.ArithmeticOpArguments\032\036" +
      ".calculator.ArithmeticOpResult\"\000\022P\n\013Prim" +
      "eTester\022 .calculator.PrimeTesterArgument" +
      "s\032\035.calculator.PrimeTesterResult\"\000B\037\n\003ge" +
      "nB\026CalculatorServiceProtoP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_calculator_ArithmeticOpArguments_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_calculator_ArithmeticOpArguments_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_calculator_ArithmeticOpArguments_descriptor,
        new java.lang.String[] { "OpType", "Args", });
    internal_static_calculator_ArithmeticOpResult_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_calculator_ArithmeticOpResult_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_calculator_ArithmeticOpResult_descriptor,
        new java.lang.String[] { "Res", });
    internal_static_calculator_PrimeTesterArguments_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_calculator_PrimeTesterArguments_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_calculator_PrimeTesterArguments_descriptor,
        new java.lang.String[] { "Number", });
    internal_static_calculator_PrimeTesterResult_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_calculator_PrimeTesterResult_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_calculator_PrimeTesterResult_descriptor,
        new java.lang.String[] { "IsPrime", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
