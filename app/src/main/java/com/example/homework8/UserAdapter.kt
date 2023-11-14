package com.example.homework8

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homework8.databinding.UserItemBinding

class UserAdapter(private val users: MutableList<Users>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private var onDeleteClickListener: OnDeleteClickListener? = null
    private var onUpdateClickListener: OnUpdateClickListener? = null

    class ViewHolder(binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val firstName: TextView = binding.firstName
        val lastName: TextView = binding.lastName
        val email: TextView = binding.email
        val deleteBtn: ImageButton = binding.deleteBtn
        val editBtn: ImageButton = binding.editBtn
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    interface OnDeleteClickListener {
        fun onDeleteClick(position: Int)
    }

    interface OnUpdateClickListener {
        fun onUpdateClick(position: Int)
    }

    fun setOnDeleteClickListener(listener: OnDeleteClickListener) {
        onDeleteClickListener = listener
    }

    fun setOnUpdateClickListener(listener: OnUpdateClickListener) {
        onUpdateClickListener = listener
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = users[position]
        holder.firstName.text = currentItem.firstName
        holder.lastName.text = currentItem.lastName
        holder.email.text = currentItem.email

        holder.deleteBtn.setOnClickListener {
            onDeleteClickListener?.onDeleteClick(position)
        }

        holder.editBtn.setOnClickListener {
            onUpdateClickListener?.onUpdateClick(position)
        }
    }

    fun removeItem(position: Int) {
        if (position in 0 until users.size) {
            users.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, itemCount - position)
        }
    }
}
