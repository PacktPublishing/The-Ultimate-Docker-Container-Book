import React, { useState } from "react";
import { v4 as uuidv4 } from 'uuid';
import api from "../../services/api";

const UploadPhoto = () => {
  const [photo, setPhoto] = useState({});
  const [id, setId] = useState(null);
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState(null);

  function onImageChange(e) {
    setPhoto(
      {
        picturePreview : URL.createObjectURL(e.target.files[0]),
        pictureAsFile: e.target.files[0],
      }
    );
    let id = uuidv4();
    console.log(`Photo ID: ${id}`);
    setId(id);
  }

  const onTitleChanged = (e) => {
    setTitle(e.target.value);
  }

  const onDescriptionChanged = (e) => {
    setDescription(e.target.value);
  }

  const postPhoto = async (e) => {
    const formData = new FormData();
    formData.append("id", id);
    formData.append("speciesId", 1);
    formData.append("raceId", 1);
    formData.append("title", title);
    formData.append("extension", null);
    formData.append("description", description);
    formData.append("file", photo.pictureAsFile);
    console.log(photo.pictureAsFile);
    const res = await api.postPhoto(formData);
    console.log(res);
    if(res.status === 200){
      setPhoto({});
      setId(null);
      setTitle("");
      setDescription(null);
      document.getElementById('file-picker').value = '';
    }
  };

  return (
    <>
      <h2>Upload Photo</h2>
      <input id="file-picker" type="file" accept="image/*" onChange={onImageChange}/>
      <div>
        { 
          (photo.picturePreview) && 
            <>
            <img src={ photo.picturePreview } alt=""/>
            <div>ID: <span >{id}</span></div>
            <div>Title: <input type="text" placeholder="Title of photo" value={title} onChange={onTitleChanged}/></div>
            <div>Description: <textarea placeholder="Description" value={description} onChange={onDescriptionChanged}/></div>
            <button onClick={postPhoto}>Upload</button>
            </>
        }
      </div>
    </>
  )
}

export default UploadPhoto;