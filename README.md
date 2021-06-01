# MedInfo
Repository Proyek Bangkit MedInfo, a Bangkit final project of our team.

Project ID: B21-CAP0127
Project Name: MedInfo
Project Themes: Healthcare
Project Team Members:
1. Andreas Genta Exna Poetra (A1281631) - Mobile Programming (Android) - Politeknik Negeri Jakarta
2. Egi Puspita Sari (A2112061) - Mobile Programming (Android) - Universitas Indraprasta PGRI
3. Violita Ananda Astria (C0050385) - Cloud Computing - Universitas Bina Nusantara
4. Faza Nur Wafirudin (C1281626) - Cloud Computing - Politeknik Negeri Jakarta
5. Kenzi Lamberto (M0010001) - Machine Learning - Institut Pertanian Bogor
6. Fadlan Hamid Alfebi (M0131302) - Machine Learning - Universitas Terbuka

There are 3 big steps in the planned project:
1. detecting text (medicine name) and creating bounding box around it,
2. sending the image within the bounding box to OCR to recognize the text,
3. giving the medicinal information for the medicine.

So far we have reached the second step in the jupyter notebook (using tesseract OCR, not yet in house) to some extent, however we have a hard time moving it to the android app.
The android app currently only works as a text detection app (and creating the bounding box).


Steps for research reproduction:
1. use generate_tfrecord.py to make tfrecord from the created dataset (ICDAR 2015 Robust Text labeled using labelimg) 
2. use training_tf2_Object_detection_model.ipynb to train the model (this notebook use the Object Detection API, this notebook also convert the model to tflite)
3. use text_detection_1.ipynb to see current progress of the project to 2nd step (still need many improvement, and it's just a notebook, not a fully fledged app)
    Note that the visualization_utils.py has been modified a little from the normal one in Object Detection API
4. app-debug.apk is the current apk for the first step of the project (text detection using tflite, based on codelab object detection)

