package br.com.fiap.nac.nac2.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.fiap.nac.nac2.R
import br.com.fiap.nac.nac2.model.Score
import kotlinx.android.synthetic.main.ranking_item.view.*

class RankingAdapter(
    private val scores: List<Score>,
    private val context: Context,
    val listener: (Score) -> Unit) : Adapter<RankingAdapter.ViewHolder>()
{
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = scores[position]
        holder?.let {
            it.bindView(note, listener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.ranking_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return scores.size
    }

    interface ClickListener {
        fun onClick(view: View, position: Int)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(score: Score,
                     listener: (Score) -> Unit) = with(itemView) {

            tvInputName.text = score.nome
            tvInputScore.text = score.pontos.toString()

            setOnClickListener { listener(score) }
        }
    }
}