package com.example.poo_1

sealed class TaskCategory {
    object Personal: TaskCategory();
    object Business: TaskCategory();
    object Tasks: TaskCategory();
    object Other: TaskCategory();
}
/**
data class GameModel(val title: String, val serialNumber: String, val error: GameError)

sealed class GameError() {
    object RayadoError : GameError()
    object InternetError : GameError()
    object NoError : GameError()
    object CongeladoError : GameError()
    data class VersionError(val version: String) : GameError()
}

class MainActivity : AppCompatActivity() {
    val gameList = listOf<GameModel>(
        GameModel(title = "Mario", serialNumber = "0987654321", InternetError),
        GameModel(title = "Mario 2", serialNumber = "09876543241", RayadoError),
        GameModel(title = "Mario 3", serialNumber = "098716543241", NoError),
        GameModel(title = "Mario 3", serialNumber = "098716543241", VersionError),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gameList.forEach { gameModel ->
            when(gameModel.error){
                InternetError -> llevarAlSoporteTecnico()
                NoError -> vender()
                RayadoError -> eliminarJuego()
                CongeladoError -> revisar()
            }
        }
    }
}
*/