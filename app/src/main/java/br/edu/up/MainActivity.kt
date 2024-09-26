package br.edu.up

import android.os.Bundle
import android.widget.Space
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.up.ui.theme.MunchkinCardGameTheme

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

@Composable
fun LayoutMain(){
    var jogadores = remember { mutableStateListOf(
        Jogador("Jogador 1"),
        Jogador("Jogador 2"),
        Jogador("Jogador 3"),
        Jogador("Jogador 4"),
        Jogador("Jogador 5"),
        Jogador("Jogador 6")
    )}

    var atual by remember { mutableStateOf(0) }

    val context = LocalContext.current;


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
                Image(
                    painter = painterResource(id = R.drawable.esquerda),
                    contentDescription = "trocar jogador - seta para esquerda",
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f));

            TextField(value = jogadores[atual].nome, onValueChange = {jogadores[atual].nome = it}, label={Text(text = "Nome do Jogador ${atual+1}")})

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
                jogadores[atual].diminuirAtributo("level", context)
            }) {
                Text("-")
            }
            Spacer(modifier = Modifier.weight(1f));

            Text("Level: ")

            Text(text = "${jogadores.get(atual).level}")

            Spacer(modifier = Modifier.weight(1f));

            Button(onClick = {
                jogadores[atual].aumentarAtributo("level", context)
            }) {
                Text("+")
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier.width(250.dp)
        ) {
            Button(onClick = {
                jogadores[atual].diminuirAtributo("equipamento", context)
            }) {
                Text("-")
            }
            Spacer(modifier = Modifier.weight(1f));

            Text("Bônus equipamento: ")
            Text(text = "${jogadores.get(atual).bonusEquipamento}")

            Spacer(modifier = Modifier.weight(1f));

            Button(onClick = {
                jogadores[atual].aumentarAtributo("equipamento", context)
            }) {
                Text("+")
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier.width(250.dp)
        ) {
            Button(onClick = {
                jogadores[atual].diminuirAtributo("modificadores", context)
            }) {
                Text("-")
            }
            Spacer(modifier = Modifier.weight(1f));

            Text("Modificadores: ")
            Text(text = "${jogadores[atual].modificadores}")

            Spacer(modifier = Modifier.weight(1f));

            Button(onClick = {
                jogadores[atual].aumentarAtributo("modificadores", context)
            }) {
                Text("+")
            }
        }
    }
}