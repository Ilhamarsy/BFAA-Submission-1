package com.dicoding.githubuser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.dicoding.githubuser.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    companion object{
        const val EXTRA_DATA ="extra_data"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<User>(EXTRA_DATA) as User
        val share = "Name : ${data.name}\nUsername : ${data.username}\nFollowers : ${data.followers}\nFollowing : ${data.following}\nRepository : ${data.repository}\nLocation : ${data.location}\nCompany : ${data.company}"

        Glide.with(this)
            .load(data.avatar)
            .circleCrop()
            .into(binding.imgUser)

        binding.apply {
            tvFollowers.text = data.followers
            tvFollowing.text = data.following
            tvRepository.text = data.repository
            tvName.text = data.name
            tvUsername.text = data.username
            tvLocation.text = data.location
            tvCompany.text = data.company
            btnShare.setOnClickListener{
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, share)

                val chooser = Intent.createChooser(intent, "Share on")
                startActivity(chooser)
            }
        }


    }
}