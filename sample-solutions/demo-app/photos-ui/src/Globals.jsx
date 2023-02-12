import React from 'react';

export const AppSettingsContext = React.createContext(
  {
    apiUrl: process.env.REACT_APP_API_URL 
  }
);