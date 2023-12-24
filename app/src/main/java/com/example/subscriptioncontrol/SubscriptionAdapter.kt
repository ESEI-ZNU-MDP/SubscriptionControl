import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.subscriptioncontrol.R

class SubscriptionAdapter(private val context: Context, private val subscriptions: MutableList<Subscription>) : BaseAdapter() {

    override fun getCount(): Int {
        return subscriptions.size
    }

    override fun getItem(position: Int): Any {
        return subscriptions[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.simple_list_item, parent, false)
        val textViewSubscription = view.findViewById<TextView>(R.id.textViewSubscription)
        val buttonEdit = view.findViewById<Button>(R.id.buttonEdit)
        val buttonDelete = view.findViewById<Button>(R.id.buttonDelete)

        val subscription = getItem(position) as Subscription
        textViewSubscription.text = "${subscription.text}; price:${subscription.number1}; data:${subscription.number2}"

        buttonEdit.setOnClickListener {
            // Обработчик события редактирования
            showEditSubscriptionDialog(position)
        }

        buttonDelete.setOnClickListener {
            // Обработчик события удаления
            subscriptions.removeAt(position)
            notifyDataSetChanged()
        }

        return view
    }

    private fun showEditSubscriptionDialog(position: Int) {
        val subscription = subscriptions[position]

        val dialogView = LayoutInflater.from(context).inflate(R.layout.activity_add_subscription, null)
        val dialogBuilder = AlertDialog.Builder(context)
            .setView(dialogView)
            .setTitle("Редактировать подписку")

        val alertDialog = dialogBuilder.show()

        val editTextSubscription = dialogView.findViewById<EditText>(R.id.editTextSubscription)
        val editTextNumber1 = dialogView.findViewById<EditText>(R.id.editTextNumber1)
        val editTextNumber2 = dialogView.findViewById<EditText>(R.id.editTextNumber2)
        val buttonAdd = dialogView.findViewById<Button>(R.id.buttonAdd)

        buttonAdd.text = "edit"

        editTextSubscription.setText(subscription.text)
        editTextNumber1.setText(subscription.number1.toString())
        editTextNumber2.setText(subscription.number2.toString())

        buttonAdd.setOnClickListener {
            val updatedText = editTextSubscription.text.toString()
            val updatedNumber1 = editTextNumber1.text.toString().toInt()
            val updatedNumber2 = editTextNumber2.text.toString().toInt()

            // Обновляем данные в списке
            subscriptions[position] = Subscription(updatedText, updatedNumber1, updatedNumber2)
            notifyDataSetChanged()

            alertDialog.dismiss()
        }
    }
}