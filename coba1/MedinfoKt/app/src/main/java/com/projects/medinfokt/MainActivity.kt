package com.projects.medinfokt

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.text.FirebaseVisionText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var imgBitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        home_capture_btn.setOnClickListener(View.OnClickListener {
            dispatchTakePictureIntent()
        })

        home_detect_btn.setOnClickListener(View.OnClickListener {
            txtDetect()
        })
    }

    private fun txtDetect() {
        val img = FirebaseVisionImage.fromBitmap(imgBitmap!!)
        val detect = FirebaseVision.getInstance().onDeviceTextRecognizer
        detect.processImage(img).addOnSuccessListener(OnSuccessListener <FirebaseVisionText>{

            firebaseVisionText ->
            txtProcessing(firebaseVisionText)}).addOnFailureListener(
            OnFailureListener {
                showToast("Something Went Wrong")
            }
        )
    }

    private fun showToast(s: String) {
        Toast.makeText(baseContext,s,Toast.LENGTH_SHORT).show()
    }

    private fun txtProcessing(firebaseVisionText: FirebaseVisionText?) {
        val block = firebaseVisionText!!.textBlocks
        if(block.size==0){
            Toast.makeText(this, "no text found", Toast.LENGTH_LONG).show()
            return
        }

        for(block in firebaseVisionText.textBlocks){
            val text = block.getText()
            home_txt_view.textSize = 16f
            home_txt_view.setText(text)
        }

    }

    val REQ_IMG_CAPT = 1

    private fun dispatchTakePictureIntent(){
        val takePicInt = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if(takePicInt.resolveActivity(packageManager)!=null){
            startActivityForResult(takePicInt,REQ_IMG_CAPT)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_IMG_CAPT && resultCode == Activity.RESULT_OK){
            val extra = data!!.extras
            imgBitmap = extra!!.get("data") as Bitmap
            home_imagae_view!!.setImageBitmap(imgBitmap)
        }
    }

}