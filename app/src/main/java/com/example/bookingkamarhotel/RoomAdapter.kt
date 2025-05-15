package com.example.bookingkamarhotel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingkamarhotel.databinding.ItemRoomBinding

class RoomAdapter(private val rooms: List<Room>, private val onItemClick: (Room) -> Unit) :
    RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        // Inflate layout dengan ViewBinding
        val binding = ItemRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RoomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val room = rooms[position]
        holder.bind(room)
    }

    override fun getItemCount(): Int = rooms.size

    inner class RoomViewHolder(private val binding: ItemRoomBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(room: Room) {
            // Menampilkan data di item_room menggunakan binding
            binding.roomTitle.text = room.title
            binding.roomDescription.text = room.shortDescription
            binding.roomImage.setImageResource(room.imageRes)
            binding.roomDescription.text = room.shortDescription

            // Menangani klik pada item
            binding.root.setOnClickListener {
                onItemClick(room)
            }
        }
    }
}
