package share.dev.com.gatoapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun tileClick(view: View) {
        val selectedTile = view as Button
        var cellID = 0
        when (selectedTile.id) {
            R.id.btn1 -> cellID = 1
            R.id.btn2 -> cellID = 2
            R.id.btn3 -> cellID = 3
            R.id.btn4 -> cellID = 4
            R.id.btn5 -> cellID = 5
            R.id.btn6 -> cellID = 6
            R.id.btn7 -> cellID = 7
            R.id.btn8 -> cellID = 8
            R.id.btn9 -> cellID = 9
        }

        playGame(cellID, selectedTile)
        println("ID: $cellID")
    }

    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var activePlayer = 1


    fun playGame(cellID: Int, tileSelected: Button) {
        if (activePlayer == 1) {
            tileSelected.text = "X"
            tileSelected.setBackgroundResource(R.color.player1)
            player1.add(cellID)
            activePlayer = 2
            autoplay()
        } else {
            tileSelected.text = "O"
            tileSelected.setBackgroundResource(R.color.player2)
            player2.add(cellID)
            activePlayer = 1
        }
        checkWinner()
        tileSelected.isEnabled = false
    }

    fun checkWinner() {
        var winner = -1
        //row1
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2
        }
        //row2
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)) {
            winner = 1
        }
        if (player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winner = 2
        }
        //row3
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winner = 2
        }

        //column1
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winner = 2
        }
        //column2
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winner = 1
        }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 2
        }
        //rcolumn3
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winner = 2
        }
        //Diagonals
        if (player1.contains(1) && player1.contains(5) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(5) && player2.contains(9)) {
            winner = 2
        }
        if (player1.contains(3) && player1.contains(5) && player1.contains(7)) {
            winner = 1
        }
        if (player2.contains(3) && player2.contains(5) && player2.contains(7)) {
            winner = 2
        }
        if (winner != -1) {
            Toast.makeText(this, "Player $winner wins!", Toast.LENGTH_LONG).show()
            for(i in 1..9){
                if (player1.contains(i) || player2.contains(i)){
                    //no action need
                }else
                {
                   disableTile(i)
                }
            }

        }
    }

    /**
     * Player 1 and player2 are Arrays
     */
    fun autoplay() {
        var emptyCells = ArrayList<Int>()
        for (cellID in 1..9) {
            if (!(player1.contains(cellID) || player2.contains(cellID))) {
                emptyCells.add(cellID)
            }
        }
        var rand = Random()
        val randIndex = rand.nextInt(emptyCells.size-0)+0
        val cellID = emptyCells[randIndex]

        var button : Button?
        when(cellID){
            1 -> button = btn1
            2 -> button = btn2
            3 -> button = btn3
            4 -> button = btn4
            5 -> button = btn5
            6 -> button = btn6
            7 -> button = btn7
            8 -> button = btn8
            9 -> button = btn9
            else ->{
                button = btn1
            }
        }
        playGame(cellID,button)
    }
    fun disableTile(index : Int){

        when (index) {
            1 -> btn1.isEnabled = false
            2 -> btn2.isEnabled = false
            3 -> btn3.isEnabled = false
            4 -> btn4.isEnabled = false
            5 -> btn5.isEnabled = false
            6 -> btn6.isEnabled = false
            7 -> btn7.isEnabled = false
            8 -> btn8.isEnabled = false
            9 -> btn9.isEnabled = false
        }
    }

}
