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

class BestFoodsAdapter(private val items: ArrayList<Foods>) :
    RecyclerView.Adapter<BestFoodsAdapter.ViewHolder>() {

    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val itemView = LayoutInflater.from(context).inflate(R.layout.viewholder_best_deal, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = items[position]

        with(holder) {
            titleTxt.text = currentItem.Title
            priceTxt.text = "${currentItem.Price}"
            timeTxt.text = "${currentItem.TimeValue}min"
            starTxt.text = "${currentItem.Star}"

            Glide.with(context!!)
                .load(currentItem.ImagePath)
                .transform(CenterCrop(), RoundedCorners(30))
                .into(pic)

            itemView.setOnClickListener {
                // Open DetailsActivity and pass the selected Foods object
                val intent = Intent(context, DetailsActivity2::class.java)
                intent.putExtra("FOOD_OBJECT_KEY", currentItem)
                context?.startActivity(intent)
            }

        }
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTxt: TextView = itemView.findViewById(R.id.titletxt)
        val priceTxt: TextView = itemView.findViewById(R.id.price)
        val starTxt: TextView = itemView.findViewById(R.id.starttxt)
        val timeTxt: TextView = itemView.findViewById(R.id.Timetxt)
        val pic: ImageView = itemView.findViewById(R.id.pic)
    }
}
