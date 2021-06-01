import pytesseract as tess
tess.pytesseract.tesseract_cmd = r'K:\Program files\Tesseract-OCR\tesseract.exe'
from PIL import Image

img = Image.open('cropped.png')
text = tess.image_to_string(img)

print(text)
