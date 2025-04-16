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
import com.example.foodordering.Activity.ListFoodActivity
import com.example.foodordering.Domain.Category
import com.example.foodordering.Domain.Foods
import com.example.foodordering.R
import java.util.ArrayList


class CategoryAdapter(private val items: ArrayList<Category>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder__category, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentCategory = items[position]

        holder.name.text = currentCategory.Name

        // Définir le fond en fonction de la position
        val backgroundResourceId = when (position) {
            0 -> R.drawable.cat_1_back
            1 -> R.drawable.cat_2_back
            2 -> R.drawable.cat_3_back
            3 -> R.drawable.cat_4_back
            4 -> R.drawable.cat_5_back
            5, 6, 7 -> R.drawable.cat_6_back
            else -> R.drawable.cat_1_back
        }

        holder.pic.setBackgroundResource(backgroundResourceId)

        // Chargement d'une image avec Glide
        context?.let {
            val drawableResourceId = it.resources.getIdentifier(
                currentCategory.ImagePath,
                "drawable",
                holder.itemView.context.packageName
            )

            Glide.with(it)
                .load(drawableResourceId)
                .into(holder.pic)

            // Gestion du clic sur l'élément de la liste
            holder.itemView.setOnClickListener { v ->
                val intent = Intent(context, ListFoodActivity::class.java).apply {
                    putExtra("categoryId", currentCategory.Id)
                    putExtra("categoryName", currentCategory.Name)
                }
                context?.startActivity(intent)
            }}
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.catNameTxt)
        var pic: ImageView = itemView.findViewById(R.id.imgCat)
    }
}
