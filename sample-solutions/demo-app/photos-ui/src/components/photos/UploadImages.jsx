import React, { useState, useEffect } from "react";

export default function UploadImages() {
  const [images, setImages] = useState([]);
  const [imageURLs, setImageURLs] = useState([]);

  useEffect(() => {
    if (images.length < 1) return;
    const newImageUrls = [];
    images.forEach(image => newImageUrls.push(URL.createObjectURL(image)));
    setImageURLs(newImageUrls);
  }, [images]);

  function onImageChange(e) {
    setImages([...e.target.files]);
  }

  const postImages = async (e) => {
    const formData = new FormData();
    formData.append("file", images[0]);
    const res = await fetch("http://localhost:4000/photos", {
      method: "post",
      headers: { "Content-Type": "multipart/form-data" },
      body: formData,
    });
    console.log(res);
  };

  return (
    <>
      <h2>Upload Photos</h2>
      <input type="file" multiple accept="image/*" onChange={onImageChange}/>
      <div>
        { imageURLs.map(imageSrc => <img src={imageSrc} />) }
      </div>
      <button onClick={postImages}>Upload</button>
    </>
  )
}