const curso = {
    codigo: 1,
    nome: "Ciencia da Comp"
};

const aluno = {
    nome: "Pexe",
    periodo: 6,
    curso: {
        codigo: 1,
        nome: "Ciencia da Comp"
    }
};

const matricula = {
    ano: 2021,
    semestre: 2,
    nota: 6.6,
    faltas: 4,
    disciplina: {
        codigo: 1
    },
    aluno: {
        ra: 1
    }
}

const attMatricula = {
    ano: 2021,
    semestre: 2,
    nota: 8.8,
    faltas: 4,
    disciplina: {
        codigo: 1
    },
    aluno: {
        ra: 1
    }
}

const getMatricula = {
    ano: 2021,
    semestre: 2,
    disciplina: {
        codigo: 1
    }
}

const disciplina = {
    nome: "SD",
    professor: "Campiolo",
    curso: {
        codigo: 1,
        nome: "Ciencia da Comp"
    }
}

exports.inserts = {
    aluno,
    curso,
    matricula,
    attMatricula,
    getMatricula,
    disciplina
};