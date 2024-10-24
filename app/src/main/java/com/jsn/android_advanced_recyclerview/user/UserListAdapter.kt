package com.jsn.android_advanced_recyclerview.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jsn.android_advanced_recyclerview.databinding.ItemUserBinding
import com.jsn.android_advanced_recyclerview.model.User

class UserListAdapter(users: List<User>) :
    RecyclerView.Adapter<UserListAdapter.UserListViewHolderWithBinding>() {

    private val userList = users

    //Using Layout
//    inner class UserListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val userNameTv: TextView = itemView.findViewById(R.id.user_name_tv)
//        val userIdTv: TextView = itemView.findViewById(R.id.user_id_tv)
//
//    }

    //Using DataBinding
    inner class UserListViewHolderWithBinding(val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserListViewHolderWithBinding {
        //Using Layout
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
//        return UserListViewHolder(view)

        //Using DataBinding
        val mBinding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserListViewHolderWithBinding(mBinding)
    }

    override fun getItemCount() = Int.MAX_VALUE

    override fun onBindViewHolder(holder: UserListViewHolderWithBinding, position: Int) {
        val posN = position % userList.size
        val user = userList.getOrNull(posN)

        //Using Layout
//        user?.let {
//            holder.userNameTv.text = it.name
//            holder.userIdTv.text = it.id
//        }
        //Using Data Binding
        user?.let {
            holder.binding.userNameTv.text = it.name
            holder.binding.userIdTv.text = it.id

        }
    }
}