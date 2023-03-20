const express = require('express');
const mustacheExpress = require('mustache-express');
const os = require('os');
const { Pool } = require('pg');
 
const app = express();
app.use(express.static('public'))
app.set('view engine', 'html');
app.engine('html', mustacheExpress());          // register file extension
app.set('views', __dirname);

const port = 3000;
const dbhost = process.env.DB_HOST || 'localhost';
console.log(`DB_HOST: ${dbhost}`);
const pool = new Pool({
    host: dbhost,
    user: 'dockeruser',
    password: 'dockerpass',
    database: 'pets',
    port: 5432,
})

app.get('/', (req,res) => {
    res.status(200).send('Wild Animals of Massai Mara National Park');
});

app.get('/images', async (req, res) => {
    const result = await pool.query('SELECT * FROM images');
    res.status(200).json({ info: result.rows });
  })
  
app.get('/animal', async (req,res) => {
    const imageId = getRandomInt(12) + 1;
    const result = await pool.query('SELECT * FROM images WHERE imageid=$1', [imageId]);
    const url = result.rows[0].url;
    res.render('index', {
            url: url,
            hostname: os.hostname()
        });
});

function getRandomInt(max) {
    return Math.floor(Math.random() * Math.floor(max));
}

app.listen(port, '0.0.0.0', () => {
    console.log(`Application listening on port ${port}`)
})