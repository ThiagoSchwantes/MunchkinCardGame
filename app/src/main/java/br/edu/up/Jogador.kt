package br.edu.up

data class Jogador(
    //não sabia me lembrava que dava pra inicializar logo de cara uma variavel
    var nome: String,
    var level: Int = 1,
    var bonusEquipamento: Int = 0,
    var modificadores: Int = 0
) {
   //não sabia da opção get
    val poderTotal get() = level + bonusEquipamento + modificadores
}