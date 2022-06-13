package com.example.appbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerViewBooks: RecyclerView
    private lateinit var booksAdapter: BooksAdapter
    private lateinit var imageSearch: ImageView
    private lateinit var editSearch: EditText
    private lateinit var homeButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerViewBooks = findViewById(R.id.RecycleViewBooks)
        imageSearch = findViewById(R.id.imageSearch)
        editSearch = findViewById(R.id.editSearch)
        homeButton = findViewById(R.id.HomeButton)

        val items = listOf(
            Book("Le Banquet/Phèdre", "Symposium attempts to find the ultimate manifestation of the love that controls the world, leading to mystic union with eternal & supercosmic beauty. Phaedrus discusses the psychology of love, resulting in the concept of the familiar Platonic \"forms\" as objects of transcendental emotion.", R.drawable.book1, 3.99F, "Platon"),
            Book("L'art d'avoir toujours raison ", "S'installer sur les positions d'autrui, épouser le mouvement du raisonnement de la partie adverse pour en exploiter les faiblesses : l'art de la discussion, c'est l'art de la guerre. Schopenhauer sait que les mots et les arguments sont des poignards dont la pointe peut tuer ; il sait aussi que la seule réalité qui vaille est notre propre victoire, même si le vrai maître du jeu reste finalement le langage et ses ressources infinies.", R.drawable.book2, 3.53F, "Arthur Schopenhauer"),
            Book("Apology", "The Apology of Socrates is Plato's version of the speech given by Socrates as he unsuccessfully defended himself in 399BCE against the charges of \"corrupting the young, and by not believing in the gods in whom the city believes, but in other daimonia that are novel\" (24b). \"Apology\" here has its earlier meaning (now usually expressed by the word \"apologia\") of speaking in defense of a cause or of one's beliefs or actions.\n" + "\n" + "The revised edition of this popular textbook features revised vocabulary and grammatical notes that now appear on the same page as the text, sentence diagrams, principal parts of verbs listed both by Stephanus page and alphabetically, word frequency list for words occurring more than twice, and complete vocabulary.", R.drawable.book3, 4.20F, "Platon"),
            Book("Le bonheur avec Spinoza - L'Ethique reformulée pour notre temps", " Spinoza est peut-être le plus grand philosophe de l'Occident, mais il est si difficile à lire que très peu arrivent à le comprendre. Voici son Ethique rendue enfin accessible à tous dans une version simplifiée et modernisée enrichie de préciauses explications et de nombreux exemples.", R.drawable.book4, 3.87F, " Bruno Giuliani"),
        )

        booksAdapter = BooksAdapter(items)

        recyclerViewBooks.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = booksAdapter
        }

        editSearch.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                val search = editSearch.text.toString().dropLastWhile { !it.isLetter() }
                booksAdapter.filter.filter(search)
                editSearch.text.clear()
                return@OnKeyListener true
            }
            false
        })

        imageSearch.setOnClickListener {
            val search = editSearch.text.toString()
            booksAdapter.filter.filter(search)
            editSearch.text.clear()
        }

        homeButton.setOnClickListener{
            val search = editSearch.text.toString()
            booksAdapter.filter.filter(search)
            editSearch.text.clear()
        }
    }
}