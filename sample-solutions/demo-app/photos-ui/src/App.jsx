import './App.css';
import {
  Routes,
  Route,
  Link
} from 'react-router-dom';
import Races from './components/races/Races';
import Species from './components/species/Species';
import PhotoGallery from './components/photos/PhotosGallery';
import UploadPhoto from './components/photos/UploadPhoto';

const App = () => {
  return (
    <div className="App">
      <div>
        <header className="App-header">
          <h1>Animal Photos</h1>
        </header>
        <nav>
            <Link to='/'>Home</Link> |&nbsp;
            <Link to='/upload'>Upload Photos</Link> |&nbsp;
            <Link to='/species'>Species</Link> |&nbsp;
            <Link to='/races'>Races</Link>
        </nav>
      </div>
      <Routes>
        <Route path='/' element={<PhotoGallery />} />
        <Route path='/species' element={<Species />} />
        <Route path='/races' element={<Races />} />
        <Route path='/upload' element={<UploadPhoto />} />
      </Routes>
    </div> 
  );
}

export default App;