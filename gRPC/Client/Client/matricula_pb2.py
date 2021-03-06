# -*- coding: utf-8 -*-
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: matricula.proto
"""Generated protocol buffer code."""
from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from google.protobuf import reflection as _reflection
from google.protobuf import symbol_database as _symbol_database
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()




DESCRIPTOR = _descriptor.FileDescriptor(
  name='matricula.proto',
  package='proto',
  syntax='proto3',
  serialized_options=None,
  create_key=_descriptor._internal_create_key,
  serialized_pb=b'\n\x0fmatricula.proto\x12\x05proto\"\x14\n\x04Mess\x12\x0c\n\x04mess\x18\x01 \x01(\t\"\x17\n\x07MessInt\x12\x0c\n\x04mess\x18\x01 \x01(\x05\"%\n\x05\x43urso\x12\x0e\n\x06\x63odigo\x18\x01 \x01(\x05\x12\x0c\n\x04nome\x18\x02 \x01(\t\"H\n\x05\x41luno\x12\n\n\x02ra\x18\x01 \x01(\x05\x12\x0c\n\x04nome\x18\x02 \x01(\t\x12\x0f\n\x07periodo\x18\x03 \x01(\x05\x12\x14\n\x0c\x63odigo_curso\x18\x04 \x01(\x05\"S\n\nDisciplina\x12\x0e\n\x06\x63odigo\x18\x01 \x01(\t\x12\x0c\n\x04nome\x18\x02 \x01(\t\x12\x11\n\tprofessor\x18\x03 \x01(\t\x12\x14\n\x0c\x63odigo_curso\x18\x04 \x01(\x05\"\x81\x01\n\tMatricula\x12\n\n\x02id\x18\x01 \x01(\x05\x12\x10\n\x08ra_aluno\x18\x02 \x01(\x05\x12\x19\n\x11\x63odigo_disciplina\x18\x03 \x01(\x03\x12\x0b\n\x03\x61no\x18\x04 \x01(\x05\x12\x10\n\x08semestre\x18\x05 \x01(\x05\x12\x0c\n\x04nota\x18\x06 \x01(\x02\x12\x0e\n\x06\x66\x61ltas\x18\x07 \x01(\x05\x32\xb8\x02\n\x10MatriculaService\x12/\n\x0c\x63\x61\x64\x61straNota\x12\x10.proto.Matricula\x1a\x0b.proto.Mess\"\x00\x12/\n\x0c\x61tualizaNota\x12\x10.proto.Matricula\x1a\x0b.proto.Mess\"\x00\x12-\n\nremoveNota\x12\x10.proto.Matricula\x1a\x0b.proto.Mess\"\x00\x12/\n\x0c\x63onsultaNota\x12\x10.proto.Matricula\x1a\x0b.proto.Mess\"\x00\x12\x30\n\rconsultaFalta\x12\x10.proto.Matricula\x1a\x0b.proto.Mess\"\x00\x12\x30\n\rconsultaAluno\x12\x10.proto.Matricula\x1a\x0b.proto.Mess\"\x00\x62\x06proto3'
)




_MESS = _descriptor.Descriptor(
  name='Mess',
  full_name='proto.Mess',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  create_key=_descriptor._internal_create_key,
  fields=[
    _descriptor.FieldDescriptor(
      name='mess', full_name='proto.Mess.mess', index=0,
      number=1, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
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
  serialized_start=26,
  serialized_end=46,
)


_MESSINT = _descriptor.Descriptor(
  name='MessInt',
  full_name='proto.MessInt',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  create_key=_descriptor._internal_create_key,
  fields=[
    _descriptor.FieldDescriptor(
      name='mess', full_name='proto.MessInt.mess', index=0,
      number=1, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
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
  serialized_start=48,
  serialized_end=71,
)


_CURSO = _descriptor.Descriptor(
  name='Curso',
  full_name='proto.Curso',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  create_key=_descriptor._internal_create_key,
  fields=[
    _descriptor.FieldDescriptor(
      name='codigo', full_name='proto.Curso.codigo', index=0,
      number=1, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
    _descriptor.FieldDescriptor(
      name='nome', full_name='proto.Curso.nome', index=1,
      number=2, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
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
  serialized_start=73,
  serialized_end=110,
)


_ALUNO = _descriptor.Descriptor(
  name='Aluno',
  full_name='proto.Aluno',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  create_key=_descriptor._internal_create_key,
  fields=[
    _descriptor.FieldDescriptor(
      name='ra', full_name='proto.Aluno.ra', index=0,
      number=1, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
    _descriptor.FieldDescriptor(
      name='nome', full_name='proto.Aluno.nome', index=1,
      number=2, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
    _descriptor.FieldDescriptor(
      name='periodo', full_name='proto.Aluno.periodo', index=2,
      number=3, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
    _descriptor.FieldDescriptor(
      name='codigo_curso', full_name='proto.Aluno.codigo_curso', index=3,
      number=4, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
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
  serialized_start=112,
  serialized_end=184,
)


_DISCIPLINA = _descriptor.Descriptor(
  name='Disciplina',
  full_name='proto.Disciplina',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  create_key=_descriptor._internal_create_key,
  fields=[
    _descriptor.FieldDescriptor(
      name='codigo', full_name='proto.Disciplina.codigo', index=0,
      number=1, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
    _descriptor.FieldDescriptor(
      name='nome', full_name='proto.Disciplina.nome', index=1,
      number=2, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
    _descriptor.FieldDescriptor(
      name='professor', full_name='proto.Disciplina.professor', index=2,
      number=3, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
    _descriptor.FieldDescriptor(
      name='codigo_curso', full_name='proto.Disciplina.codigo_curso', index=3,
      number=4, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
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
  serialized_start=186,
  serialized_end=269,
)


_MATRICULA = _descriptor.Descriptor(
  name='Matricula',
  full_name='proto.Matricula',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  create_key=_descriptor._internal_create_key,
  fields=[
    _descriptor.FieldDescriptor(
      name='id', full_name='proto.Matricula.id', index=0,
      number=1, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
    _descriptor.FieldDescriptor(
      name='ra_aluno', full_name='proto.Matricula.ra_aluno', index=1,
      number=2, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
    _descriptor.FieldDescriptor(
      name='codigo_disciplina', full_name='proto.Matricula.codigo_disciplina', index=2,
      number=3, type=3, cpp_type=2, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
    _descriptor.FieldDescriptor(
      name='ano', full_name='proto.Matricula.ano', index=3,
      number=4, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
    _descriptor.FieldDescriptor(
      name='semestre', full_name='proto.Matricula.semestre', index=4,
      number=5, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
    _descriptor.FieldDescriptor(
      name='nota', full_name='proto.Matricula.nota', index=5,
      number=6, type=2, cpp_type=6, label=1,
      has_default_value=False, default_value=float(0),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
    _descriptor.FieldDescriptor(
      name='faltas', full_name='proto.Matricula.faltas', index=6,
      number=7, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
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
  serialized_start=272,
  serialized_end=401,
)

DESCRIPTOR.message_types_by_name['Mess'] = _MESS
DESCRIPTOR.message_types_by_name['MessInt'] = _MESSINT
DESCRIPTOR.message_types_by_name['Curso'] = _CURSO
DESCRIPTOR.message_types_by_name['Aluno'] = _ALUNO
DESCRIPTOR.message_types_by_name['Disciplina'] = _DISCIPLINA
DESCRIPTOR.message_types_by_name['Matricula'] = _MATRICULA
_sym_db.RegisterFileDescriptor(DESCRIPTOR)

Mess = _reflection.GeneratedProtocolMessageType('Mess', (_message.Message,), {
  'DESCRIPTOR' : _MESS,
  '__module__' : 'matricula_pb2'
  # @@protoc_insertion_point(class_scope:proto.Mess)
  })
_sym_db.RegisterMessage(Mess)

MessInt = _reflection.GeneratedProtocolMessageType('MessInt', (_message.Message,), {
  'DESCRIPTOR' : _MESSINT,
  '__module__' : 'matricula_pb2'
  # @@protoc_insertion_point(class_scope:proto.MessInt)
  })
_sym_db.RegisterMessage(MessInt)

Curso = _reflection.GeneratedProtocolMessageType('Curso', (_message.Message,), {
  'DESCRIPTOR' : _CURSO,
  '__module__' : 'matricula_pb2'
  # @@protoc_insertion_point(class_scope:proto.Curso)
  })
_sym_db.RegisterMessage(Curso)

Aluno = _reflection.GeneratedProtocolMessageType('Aluno', (_message.Message,), {
  'DESCRIPTOR' : _ALUNO,
  '__module__' : 'matricula_pb2'
  # @@protoc_insertion_point(class_scope:proto.Aluno)
  })
_sym_db.RegisterMessage(Aluno)

Disciplina = _reflection.GeneratedProtocolMessageType('Disciplina', (_message.Message,), {
  'DESCRIPTOR' : _DISCIPLINA,
  '__module__' : 'matricula_pb2'
  # @@protoc_insertion_point(class_scope:proto.Disciplina)
  })
_sym_db.RegisterMessage(Disciplina)

Matricula = _reflection.GeneratedProtocolMessageType('Matricula', (_message.Message,), {
  'DESCRIPTOR' : _MATRICULA,
  '__module__' : 'matricula_pb2'
  # @@protoc_insertion_point(class_scope:proto.Matricula)
  })
_sym_db.RegisterMessage(Matricula)



_MATRICULASERVICE = _descriptor.ServiceDescriptor(
  name='MatriculaService',
  full_name='proto.MatriculaService',
  file=DESCRIPTOR,
  index=0,
  serialized_options=None,
  create_key=_descriptor._internal_create_key,
  serialized_start=404,
  serialized_end=716,
  methods=[
  _descriptor.MethodDescriptor(
    name='cadastraNota',
    full_name='proto.MatriculaService.cadastraNota',
    index=0,
    containing_service=None,
    input_type=_MATRICULA,
    output_type=_MESS,
    serialized_options=None,
    create_key=_descriptor._internal_create_key,
  ),
  _descriptor.MethodDescriptor(
    name='atualizaNota',
    full_name='proto.MatriculaService.atualizaNota',
    index=1,
    containing_service=None,
    input_type=_MATRICULA,
    output_type=_MESS,
    serialized_options=None,
    create_key=_descriptor._internal_create_key,
  ),
  _descriptor.MethodDescriptor(
    name='removeNota',
    full_name='proto.MatriculaService.removeNota',
    index=2,
    containing_service=None,
    input_type=_MATRICULA,
    output_type=_MESS,
    serialized_options=None,
    create_key=_descriptor._internal_create_key,
  ),
  _descriptor.MethodDescriptor(
    name='consultaNota',
    full_name='proto.MatriculaService.consultaNota',
    index=3,
    containing_service=None,
    input_type=_MATRICULA,
    output_type=_MESS,
    serialized_options=None,
    create_key=_descriptor._internal_create_key,
  ),
  _descriptor.MethodDescriptor(
    name='consultaFalta',
    full_name='proto.MatriculaService.consultaFalta',
    index=4,
    containing_service=None,
    input_type=_MATRICULA,
    output_type=_MESS,
    serialized_options=None,
    create_key=_descriptor._internal_create_key,
  ),
  _descriptor.MethodDescriptor(
    name='consultaAluno',
    full_name='proto.MatriculaService.consultaAluno',
    index=5,
    containing_service=None,
    input_type=_MATRICULA,
    output_type=_MESS,
    serialized_options=None,
    create_key=_descriptor._internal_create_key,
  ),
])
_sym_db.RegisterServiceDescriptor(_MATRICULASERVICE)

DESCRIPTOR.services_by_name['MatriculaService'] = _MATRICULASERVICE

# @@protoc_insertion_point(module_scope)
