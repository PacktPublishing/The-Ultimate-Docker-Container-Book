const baseUrl = process.env.REACT_APP_API_URL;

const api = {
  getSpecies: () => fetch(`${baseUrl}/species`),
  getRaces: () => fetch(`${baseUrl}/races`),

  postPhoto: (body) => fetch(`${baseUrl}/photos`, {
    method: "post",
    // NOTE: if you uncomment the following line, it won't work (sigh)
    // headers: { "Content-Type": "multipart/form-data" },
    body: body,
  })
}

export default api;
