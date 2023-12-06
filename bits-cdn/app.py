import time
from flask_cors import CORS, cross_origin
from flask import Flask, request, jsonify, send_file
from werkzeug.utils import secure_filename
import os
from PIL import Image

app = Flask(__name__)
CORS(app)

# Set the absolute upload folder path and allowed extensions
UPLOAD_FOLDER = os.path.abspath('uploads')
ALLOWED_EXTENSIONS = {'png', 'jpg', 'jpeg', 'gif'}
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER

def allowed_file(filename):
    return '.' in filename and filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS

@app.route('/image', methods=['POST'])
@cross_origin()
def upload_image():
    # Check if the POST request has a file part
    #if 'file' not in request.files:
        #return jsonify({'error': 'No file part'}), 401

    file = request.files['file']

    # Check if the file is empty
    if file.filename == '':
        return jsonify({'error': 'No selected file'}), 402

    # Check if the file has an allowed extension
    if file and allowed_file(file.filename):
        # Generate filename using Unix timestamp
        file_id = str(int(time.time())) 
        file_ext = file.filename.split('.')[-1]

        filename = file_id + '.' + file_ext
        
        # Save the file to the absolute upload folder path
        file_path = os.path.join(app.config['UPLOAD_FOLDER'], filename)
        file.save(file_path)
        
        # Resize the image to 300x300 pixels
        img = Image.open(file_path)
        img = img.resize((300, 300))
        img.save(file_path)

        return jsonify({'filename': file_id + '_' + file_ext}), 201

    return jsonify({'error': 'Invalid file type'}), 403

@app.route('/image/<string:file_name>', methods=['GET'])
@cross_origin()
def get_image(file_name):
    file_id = file_name[:len(file_name) - 3]
    file_ext = file_name[len(file_name) - 3 :]
    
    filename = file_id + '.' + file_ext
    # Check if the file exists
    
    file_path = os.path.join(app.config['UPLOAD_FOLDER'], filename)
    if os.path.exists(file_path):
        return send_file(file_path, as_attachment=True), 200
    else:
        return jsonify({'error': 'File not found'}), 404

if __name__ == '__main__':
    # Create the absolute upload folder if it doesn't exist
    if not os.path.exists(UPLOAD_FOLDER):
        os.makedirs(UPLOAD_FOLDER)

    app.run(debug=True)
