const baseUrl = process.env.REACT_APP_API_URL;

const api = {
  getSpecies: () => fetch(`${baseUrl}/species`),
  getRaces: () => fetch(`${baseUrl}/races`),

  postSpecies: (body) => {
    const options = {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: body,
    };
    console.log(options);
    return fetch(`${baseUrl}/species`, options);
  },

  postRace: (body) => {
    const options = {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: body,
    };
    console.log(options);
    return fetch(`${baseUrl}/races`, options)
  },

  postPhoto: (body) => fetch(`${baseUrl}/photos`, {
    method: "post",
    // NOTE: if you uncomment the following line, it won't work (sigh)
    // headers: { "Content-Type": "multipart/form-data" },
    body: body,
  })
}

export default api;
