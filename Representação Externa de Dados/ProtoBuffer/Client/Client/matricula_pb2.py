# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: matricula.proto

import sys
_b=sys.version_info[0]<3 and (lambda x:x) or (lambda x:x.encode('latin1'))
from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from google.protobuf import reflection as _reflection
from google.protobuf import symbol_database as _symbol_database
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()




DESCRIPTOR = _descriptor.FileDescriptor(
  name='matricula.proto',
  package='',
  syntax='proto3',
  serialized_options=None,
  serialized_pb=_b('\n\x0fmatricula.proto\"f\n\tMatricula\x12\x10\n\x08ra_aluno\x18\x01 \x01(\r\x12\x0b\n\x03\x61no\x18\x02 \x01(\r\x12\x10\n\x08semestre\x18\x03 \x01(\r\x12\x0c\n\x04nota\x18\x04 \x01(\x01\x12\x0e\n\x06\x66\x61ltas\x18\x05 \x01(\r\x12\n\n\x02op\x18\x06 \x01(\rb\x06proto3')
)




_MATRICULA = _descriptor.Descriptor(
  name='Matricula',
  full_name='Matricula',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='ra_aluno', full_name='Matricula.ra_aluno', index=0,
      number=1, type=13, cpp_type=3, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='ano', full_name='Matricula.ano', index=1,
      number=2, type=13, cpp_type=3, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='semestre', full_name='Matricula.semestre', index=2,
      number=3, type=13, cpp_type=3, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='nota', full_name='Matricula.nota', index=3,
      number=4, type=1, cpp_type=5, label=1,
      has_default_value=False, default_value=float(0),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='faltas', full_name='Matricula.faltas', index=4,
      number=5, type=13, cpp_type=3, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='op', full_name='Matricula.op', index=5,
      number=6, type=13, cpp_type=3, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=19,
  serialized_end=121,
)

DESCRIPTOR.message_types_by_name['Matricula'] = _MATRICULA
_sym_db.RegisterFileDescriptor(DESCRIPTOR)

Matricula = _reflection.GeneratedProtocolMessageType('Matricula', (_message.Message,), dict(
  DESCRIPTOR = _MATRICULA,
  __module__ = 'matricula_pb2'
  # @@protoc_insertion_point(class_scope:Matricula)
  ))
_sym_db.RegisterMessage(Matricula)


# @@protoc_insertion_point(module_scope)
