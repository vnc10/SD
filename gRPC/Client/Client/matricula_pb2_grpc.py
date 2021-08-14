# Generated by the gRPC Python protocol compiler plugin. DO NOT EDIT!
"""Client and server classes corresponding to protobuf-defined services."""
import grpc

import matricula_pb2 as matricula__pb2


class MatriculaServiceStub(object):
    """Missing associated documentation comment in .proto file."""

    def __init__(self, channel):
        """Constructor.

        Args:
            channel: A grpc.Channel.
        """
        self.cadastraNota = channel.unary_unary(
                '/proto.MatriculaService/cadastraNota',
                request_serializer=matricula__pb2.Matricula.SerializeToString,
                response_deserializer=matricula__pb2.Mess.FromString,
                )
        self.atualizaNota = channel.unary_unary(
                '/proto.MatriculaService/atualizaNota',
                request_serializer=matricula__pb2.Matricula.SerializeToString,
                response_deserializer=matricula__pb2.Mess.FromString,
                )
        self.removeNota = channel.unary_unary(
                '/proto.MatriculaService/removeNota',
                request_serializer=matricula__pb2.Matricula.SerializeToString,
                response_deserializer=matricula__pb2.Mess.FromString,
                )
        self.consultaNota = channel.unary_unary(
                '/proto.MatriculaService/consultaNota',
                request_serializer=matricula__pb2.Matricula.SerializeToString,
                response_deserializer=matricula__pb2.Mess.FromString,
                )
        self.consultaFalta = channel.unary_unary(
                '/proto.MatriculaService/consultaFalta',
                request_serializer=matricula__pb2.Matricula.SerializeToString,
                response_deserializer=matricula__pb2.Mess.FromString,
                )
        self.consultaAluno = channel.unary_unary(
                '/proto.MatriculaService/consultaAluno',
                request_serializer=matricula__pb2.Matricula.SerializeToString,
                response_deserializer=matricula__pb2.Mess.FromString,
                )


class MatriculaServiceServicer(object):
    """Missing associated documentation comment in .proto file."""

    def cadastraNota(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def atualizaNota(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def removeNota(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def consultaNota(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def consultaFalta(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def consultaAluno(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')


def add_MatriculaServiceServicer_to_server(servicer, server):
    rpc_method_handlers = {
            'cadastraNota': grpc.unary_unary_rpc_method_handler(
                    servicer.cadastraNota,
                    request_deserializer=matricula__pb2.Matricula.FromString,
                    response_serializer=matricula__pb2.Mess.SerializeToString,
            ),
            'atualizaNota': grpc.unary_unary_rpc_method_handler(
                    servicer.atualizaNota,
                    request_deserializer=matricula__pb2.Matricula.FromString,
                    response_serializer=matricula__pb2.Mess.SerializeToString,
            ),
            'removeNota': grpc.unary_unary_rpc_method_handler(
                    servicer.removeNota,
                    request_deserializer=matricula__pb2.Matricula.FromString,
                    response_serializer=matricula__pb2.Mess.SerializeToString,
            ),
            'consultaNota': grpc.unary_unary_rpc_method_handler(
                    servicer.consultaNota,
                    request_deserializer=matricula__pb2.Matricula.FromString,
                    response_serializer=matricula__pb2.Mess.SerializeToString,
            ),
            'consultaFalta': grpc.unary_unary_rpc_method_handler(
                    servicer.consultaFalta,
                    request_deserializer=matricula__pb2.Matricula.FromString,
                    response_serializer=matricula__pb2.Mess.SerializeToString,
            ),
            'consultaAluno': grpc.unary_unary_rpc_method_handler(
                    servicer.consultaAluno,
                    request_deserializer=matricula__pb2.Matricula.FromString,
                    response_serializer=matricula__pb2.Mess.SerializeToString,
            ),
    }
    generic_handler = grpc.method_handlers_generic_handler(
            'proto.MatriculaService', rpc_method_handlers)
    server.add_generic_rpc_handlers((generic_handler,))


 # This class is part of an EXPERIMENTAL API.
class MatriculaService(object):
    """Missing associated documentation comment in .proto file."""

    @staticmethod
    def cadastraNota(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/proto.MatriculaService/cadastraNota',
            matricula__pb2.Matricula.SerializeToString,
            matricula__pb2.Mess.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def atualizaNota(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/proto.MatriculaService/atualizaNota',
            matricula__pb2.Matricula.SerializeToString,
            matricula__pb2.Mess.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def removeNota(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/proto.MatriculaService/removeNota',
            matricula__pb2.Matricula.SerializeToString,
            matricula__pb2.Mess.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def consultaNota(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/proto.MatriculaService/consultaNota',
            matricula__pb2.Matricula.SerializeToString,
            matricula__pb2.Mess.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def consultaFalta(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/proto.MatriculaService/consultaFalta',
            matricula__pb2.Matricula.SerializeToString,
            matricula__pb2.Mess.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def consultaAluno(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/proto.MatriculaService/consultaAluno',
            matricula__pb2.Matricula.SerializeToString,
            matricula__pb2.Mess.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)