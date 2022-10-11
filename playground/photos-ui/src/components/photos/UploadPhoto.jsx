import React, { useState, useEffect } from "react";
import { v4 as uuidv4 } from 'uuid';

export default function UploadPhoto() {
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
    console.log(e.target.value);
    setTitle(e.target.value);
  }

  const onDescriptionChanged = (e) => {
    console.log(e.target.value);
    setDescription(e.target.value);
  }

  const postPhoto = async (e) => {
    const formData = new FormData();
    const name = photo.pictureAsFile.name;
    formData.append("id", id);
    formData.append("speciesId", 1);
    formData.append("raceId", 1);
    formData.append("title", title);
    formData.append("extension", null);
    formData.append("description", description);
    formData.append("file", photo.pictureAsFile);
    console.log(photo.pictureAsFile);
    const res = await fetch("http://localhost:4000/photos", {
      method: "post",
      // NOTE: if you uncomment the following line, it won't work (sigh)
      // headers: { "Content-Type": "multipart/form-data" },
      body: formData,
    });
    console.log(res);
  };

  return (
    <>
      <h2>Upload Photo</h2>
      <input type="file" accept="image/*" onChange={onImageChange}/>
      <div>
        { 
          (photo.picturePreview) && 
            <>
            <img src={ photo.picturePreview } />
            <div>ID: <span>{id}</span></div>
            <div>Title: <input type="text" title="Title of photo" value={title} onChange={onTitleChanged}/></div>
            <div>Description: <textarea title="Description" value={description} onChange={onDescriptionChanged}/></div>
            <button onClick={postPhoto}>Upload</button>
            </>
        }
      </div>
    </>
  )
}