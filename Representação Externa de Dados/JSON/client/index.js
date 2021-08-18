const Axios = require ('axios').default;
const readLine = require('readline');
const {aluno, curso, disciplina, matricula, attMatricula, getMatricula} = require('./inserts').inserts;
let menu;

function execMenu(sair){
    if(sair){
        menu.close();
        return
    }else{
        console.log("\nEssas são as operações possiveis:");
        console.log('1 - Criar Curso\n2 - Criar Disciplina\n3 - Criar Aluno\n4 - Criar Matricula\n5 - visualizar matricula\n6 - Alterar matricula\n7 - Sair');
        console.log('Por favor escolha uma operação informando seu respectivo número.')

        if(menu){
            menu.close();
        }

        menu = readLine.createInterface({
            input: process.stdin,
            output: process.stdout
        });

        menu.question('Operação: ', (input) =>{
            switch(input){
                case '1':
                    Axios.post('http://127.0.0.1:8080/sd/curso',JSON.stringify(curso), {
                        headers: {
                            'Content-Type':'application/json'
                        }
                    }).then((response) => {
                        console.log('\nOperação realizada com sucesso!');
                    }).catch((err) => {
                        console.log(err);
                    });
                    setTimeout(() => {
                        execMenu(false);
                    }, 1500);
                    break;
                case '2':
                    Axios.post('http://127.0.0.1:8080/sd/disciplina',JSON.stringify(disciplina), {
                        headers: {
                            'Content-Type':'application/json'
                        }
                    }).then((response) => {
                        console.log('\nOperação realizada com sucesso!');
                    }).catch((err) => {
                        console.log(err);
                    });
                    setTimeout(() => {
                        execMenu(false);
                    }, 1500);
                    break;
                case '3':
                    Axios.post('http://127.0.0.1:8080/sd/aluno',JSON.stringify(aluno), {
                        headers: {
                            'Content-Type':'application/json'
                        }
                    }).then((response) => {
                        console.log('\nOperação realizada com sucesso!');
                    }).catch((err) => {
                        console.log(err);
                    });
                    setTimeout(() => {
                        execMenu(false);
                    }, 1500);
                    break;
                case '4':
                    Axios.post('http://127.0.0.1:8080/sd/matricula',JSON.stringify(matricula), {
                        headers: {
                            'Content-Type':'application/json'
                        }
                    }).then((response) => {
                        console.log('\nOperação realizada com sucesso!');
                    }).catch((err) => {
                        console.log(err);
                    });
                    setTimeout(() => {
                        execMenu(false);
                    }, 1500);
                    break;
                case '5':
                    Axios.get('http://127.0.0.1:8080/sd/matricula/2021', {
                        headers: {
                            'Content-Type':'application/json'
                        }
                    }).then((response) => {
                        console.log(`\nAluno: ${response.data}`);
                    }).catch((err) => {
                        console.log(err);
                    });
                    setTimeout(() => {
                        execMenu(false);
                    }, 1500);
                    break;
                case '6':
                    Axios.put('http://127.0.0.1:8080/sd/matricula/5',JSON.stringify(attMatricula), {
                        headers: {
                            'Content-Type':'application/json'
                        }
                    }).then((response) => {
                        console.log('\nOperação realizada com sucesso!');
                    }).catch((err) => {
                        console.log(err);
                    });
                    setTimeout(() => {
                        execMenu(false);
                    }, 1500);
                    break;
                case '7':
                    console.log('\nEncerrando...\n');
                    execMenu(true);
                    break;
                default:
                    console.log('número invalido');
                    execMenu(false);
                    break;
            }
        });
    }
}

execMenu();