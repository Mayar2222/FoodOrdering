import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.foodordering.Activity.DetailsActivity2
import com.example.foodordering.Domain.Foods
import com.example.foodordering.R


class FoodListAdapter(private val items: ArrayList<Foods>) :
    RecyclerView.Adapter<FoodListAdapter.ViewHolder>() {

    private lateinit var context: Context

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTxt: TextView = itemView.findViewById(R.id.titlee)
        val priceTxt: TextView = itemView.findViewById(R.id.pricee)
        val rateTxt: TextView = itemView.findViewById(R.id.star)
        val timeTxt: TextView = itemView.findViewById(R.id.time)
        val pic: ImageView = itemView.findViewById(R.id.img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val inflate =
            LayoutInflater.from(context).inflate(R.layout.viewholder_list_food, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = items[position]

        holder.titleTxt.text = currentItem.Title
        holder.timeTxt.text = "${currentItem.TimeValue}min"
        holder.priceTxt.text = "$${currentItem.Price}"
        holder.rateTxt.text = currentItem.Star.toString()

        Glide.with(context)
            .load(currentItem.ImagePath)
            .transform(CenterCrop(), RoundedCorners(30))
            .into(holder.pic)

    }



    override fun getItemCount(): Int {
        return items.size
    }
}
