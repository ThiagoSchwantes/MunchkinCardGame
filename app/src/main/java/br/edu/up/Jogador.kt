package br.edu.up

import android.content.Context
import android.widget.Toast

class Jogador (
    var nome: String,
    var level: Int,
    var bonusEquipamento: Int,
    var modificadores: Int
){
    var poderTotal = 0;

    init {
        calcularPoderTotal()
    }

    constructor(nome: String):this(nome, 1, 0, 0)

    fun aumentarAtributo(atributo:String, context:Context){
        when{
            atributo.equals("level") && level < 10 -> level++;
            atributo.equals("equipamento") -> bonusEquipamento++
            atributo.equals("modificadores") -> modificadores++
            else -> Toast.makeText(context, "Não foi possível aumentar atributo: ${atributo}", Toast.LENGTH_SHORT);
        }
        calcularPoderTotal();
    }

    fun diminuirAtributo(atributo:String, context:Context){
        when{
            atributo.equals("level") && level > 1 -> level--
            atributo.equals("equipamento") -> bonusEquipamento--
            atributo.equals("modificadores")-> modificadores--
            else -> Toast.makeText(context, "Não foi possível diminuir atributo: ${atributo}", Toast.LENGTH_SHORT);
        }
        calcularPoderTotal()
    }

    private fun calcularPoderTotal(){
        poderTotal = level + bonusEquipamento + modificadores;
    }

    fun reset(){
        level = 1;
        bonusEquipamento = 0;
        modificadores = 0;
        calcularPoderTotal();
    }
}