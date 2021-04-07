import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ejemploeverissw.R
import com.ejemploeverissw.models.People


//Pass the ArrayList and a listener, and add a variable to hold your data//

class MyAdapter (private val peopleList : ArrayList<People>, private val listener :
    //Extend RecyclerView.Adapter//
    Listener) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    interface Listener {
        fun onItemClick(ap: Int)

    }

    //Define an array of colours//

    private val colors : Array<String> = arrayOf("#D6BF7C", "#EFB087", "#1E6431", "#79B579", "#4EA848", "#D9F6BC" , "#1A9195" , "#037691")

    //Bind the ViewHolder//

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    //Pass the position where each item should be displayed//

        holder.bind(peopleList[position], listener, colors, position)

    }

    //Check how many items you have to display//

    override fun getItemCount(): Int = peopleList.count()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        return ViewHolder(view)

    }

    //Create a ViewHolder class for your RecyclerView items//

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        //Assign values from the data model, to their corresponding Views//

        fun bind(people: People, listener: Listener, colors : Array<String>, position: Int) {

            //Listen for user input events//

            itemView.setOnClickListener{ listener.onItemClick(adapterPosition) }
            itemView.setBackgroundColor(Color.parseColor(colors[position % 8]))
            var tname = itemView.findViewById<TextView>(R.id.t_name)
            tname.text = people.name
        }
    }
}