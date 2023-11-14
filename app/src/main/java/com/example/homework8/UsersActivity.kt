package com.example.homework8

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework8.databinding.ActivityUsersBinding

class UsersActivity : AppCompatActivity(), UserAdapter.OnDeleteClickListener, UserAdapter.OnUpdateClickListener {
    private lateinit var binding: ActivityUsersBinding
    private lateinit var newRecyclerView: RecyclerView
    private lateinit var userList: MutableList<Users>
    private lateinit var launcher: ActivityResultLauncher<Intent>

    companion object {
        const val RESULT_UPDATE = 123
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        newRecyclerView = binding.recyclerView
        setupRV(newRecyclerView)

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                val firstName = data?.getStringExtra("firstName")
                val lastName = data?.getStringExtra("lastName")
                val email = data?.getStringExtra("email")

                if (firstName != null && lastName != null && email != null) {
                    userList.add(Users(firstName, lastName, email))
                    newRecyclerView.adapter?.notifyItemInserted(userList.size - 1)
                }
            } else if (result.resultCode == RESULT_UPDATE) {
                val data: Intent? = result.data
                val position = data?.getIntExtra("position", -1)

                if (position != -1) {
                    userList[position!!] = Users(
                        data.getStringExtra("firstName")!!,
                        data.getStringExtra("lastName")!!,
                        data.getStringExtra("email")!!
                    )
                    newRecyclerView.adapter?.notifyItemChanged(position)
                }
            }
        }

        userList = mutableListOf(
            Users("Luka", "Parchukidze", "lukaparchukidze@gmail.com"),
            Users("Guga", "Tevzadze", "gugat1@mgmail.com"),
            Users("Aleksandre", "Toria", "aleksandretoria1@gmail.com"),
            Users("Luka", "Parchukidze", "lukaparchukidze@gmail.com"),
            Users("Guga", "Tevzadze", "gugat1@mgmail.com"),
            Users("Aleksandre", "Toria", "aleksandretoria1@gmail.com"),
            Users("Luka", "Parchukidze", "lukaparchukidze@gmail.com"),
            Users("Guga", "Tevzadze", "gugat1@mgmail.com"),
            Users("Aleksandre", "Toria", "aleksandretoria1@gmail.com"),
            Users("Luka", "Parchukidze", "lukaparchukidze@gmail.com"),
            Users("Guga", "Tevzadze", "gugat1@mgmail.com"),
            Users("Aleksandre", "Toria", "aleksandretoria1@gmail.com")
        )

        val userAdapter = UserAdapter(userList)
        userAdapter.setOnDeleteClickListener(this)
        userAdapter.setOnUpdateClickListener(this)
        newRecyclerView.adapter = userAdapter

        binding.addBtn.setOnClickListener {
            val intent = Intent(this, AddUserActivity::class.java)
            launcher.launch(intent)
        }
    }

    private fun setupRV(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.setHasFixedSize(true)
    }

    override fun onDeleteClick(position: Int) {
        val adapter = newRecyclerView.adapter as? UserAdapter
        adapter?.removeItem(position)
    }

    override fun onUpdateClick(position: Int) {
        val intent = Intent(this, UserActivity::class.java)
        intent.putExtra("firstName", userList[position].firstName)
        intent.putExtra("lastName", userList[position].lastName)
        intent.putExtra("email", userList[position].email)
        intent.putExtra("position", position)

        launcher.launch(intent)
    }

}
