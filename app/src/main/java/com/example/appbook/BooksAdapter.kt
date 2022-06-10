package com.example.appbook

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

@Suppress("UNCHECKED_CAST")
class BooksAdapter(var items: List<Book>) : RecyclerView.Adapter<BooksAdapter.BooksViewHolder>(), Filterable {

    var booksFilteredList: List<Book> = ArrayList()

    init {
        booksFilteredList = items
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    booksFilteredList = items
                } else {

                    val resultList = ArrayList<Book>()
                    for (book in items) {
                        if (book.title.lowercase().contains(charSearch.lowercase())
                            || book.author.lowercase().contains(charSearch.lowercase())) {
                            resultList.add(book)
                        }
                        booksFilteredList = resultList
                    }
                }
                val filterResult = FilterResults()
                filterResult.values = booksFilteredList
                return filterResult
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                booksFilteredList = results?.values as List<Book>
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BooksViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        val book = booksFilteredList[position]
        holder.bind(book)
    }

    override fun getItemCount() = booksFilteredList.size

    inner class BooksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private var tvTitle: TextView
        private var tvSummary: TextView
        private var tvAuthor: TextView
        private var imageBook: ImageView
        private var ratingBar: RatingBar

        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
            tvSummary = itemView.findViewById(R.id.tvSummary)
            tvAuthor = itemView.findViewById(R.id.tvAuthor)
            imageBook = itemView.findViewById(R.id.imageBook)
            ratingBar = itemView.findViewById(R.id.ratingBar)
        }

        fun bind(book: Book) {
            tvTitle.text = book.title
            tvAuthor.text = book.author
            tvSummary.text = book.summary
            imageBook.setImageResource(book.image)
            ratingBar.rating = book.rating
        }

    }
}


