package br.edu.up

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LayoutMain();
        }
    }
}
@Composable
@Preview (showBackground = true)
fun previsualizar(){
    LayoutMain()
}

fun initJogadores(quantJogadores: Int):List<Jogador>{
    var jogadores = listOf<Jogador>();

    for (i in 1..quantJogadores){
        jogadores += Jogador("");
    }
    return jogadores;
}

@Composable
fun LayoutMain(){
    //tive que pesquisar como usar o mutableStateListOf pq enfrentei diversos problemas, mas no fim deu certo
    var jogadores = remember {mutableStateListOf<Jogador>()}
    jogadores += initJogadores(6);

    var atual by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Row (
            modifier = Modifier.fillMaxWidth().padding(top = 50.dp).padding(horizontal = 10.dp)
        ){
            Button(onClick = {
                when{
                    atual == 0 -> atual = 5;
                    else -> atual--
                }
            }) {
                //frescura minha q pesquisei como fazer
                Image(
                    painter = painterResource(id = R.drawable.esquerda),
                    contentDescription = "trocar jogador - seta para esquerda",
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f));

            TextField(
                value = jogadores[atual].nome,
                onValueChange = {
                    //quando se usa variavel = it não funciona nessa ocasião
                    // Solução: copiar a variavel atual alterando o atributo em especifico e salvando na variavel atual
                    novoNome -> jogadores[atual] = jogadores[atual].copy(nome = novoNome)
                },
                label={Text(text = "Nome do Jogador ${atual+1}")},
                placeholder = { Text("Jogador ${atual+1}") }
            )

            Spacer(modifier = Modifier.weight(1f));

            Button(onClick = {
                when{
                    atual == 5 -> atual = 0;
                    else -> atual++
                }
            }) {
                Image(
                    painter = painterResource(id = R.drawable.direita),
                    contentDescription = "trocar jogador - Seta pra direita",
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Poder Total: ${jogadores[atual].poderTotal}")
        }

    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Row(
            modifier = Modifier.width(250.dp)
        ) {
            Button(onClick = {
                //mesma coisa que a alteração do nome, mas dessa vez com um metodo que faz um limite minimo
                jogadores[atual] = jogadores[atual].copy(level = (jogadores[atual].level - 1).coerceAtLeast(1))
            }) {
                Text("-")
            }
            Spacer(modifier = Modifier.weight(1f));

            Text("Level: ")

            Text(text = "${jogadores.get(atual).level}")

            Spacer(modifier = Modifier.weight(1f));

            Button(onClick = {
                // memsma coisa que o de cima, mas com um metodo de limite máximo
                jogadores[atual] = jogadores[atual].copy(level = (jogadores[atual].level + 1).coerceAtMost(10))
            }) {
                Text("+")
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier.width(250.dp)
        ) {
            Button(onClick = {
                jogadores[atual] = jogadores[atual].copy(bonusEquipamento = (jogadores[atual].bonusEquipamento - 1).coerceAtLeast(0))
            }) {
                Text("-")
            }
            Spacer(modifier = Modifier.weight(1f));

            Text("Bônus equipamento: ")
            Text(text = "${jogadores.get(atual).bonusEquipamento}")

            Spacer(modifier = Modifier.weight(1f));

            Button(onClick = {
                jogadores[atual] = jogadores[atual].copy(bonusEquipamento = (jogadores[atual].bonusEquipamento + 1).coerceAtMost(40))
            }) {
                Text("+")
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier.width(250.dp)
        ) {
            Button(onClick = {
                jogadores[atual] = jogadores[atual].copy(modificadores = (jogadores[atual].modificadores - 1).coerceAtLeast(-5))
            }) {
                Text("-")
            }
            Spacer(modifier = Modifier.weight(1f));

            Text("Modificadores: ")
            Text(text = "${jogadores[atual].modificadores}")

            Spacer(modifier = Modifier.weight(1f));

            Button(onClick = {
                jogadores[atual] = jogadores[atual].copy(modificadores = (jogadores[atual].modificadores + 1).coerceAtMost(10))
            }) {
                Text("+")
            }
        }
    }
}