const express = require('express');
const mustacheExpress = require('mustache-express');
const os = require('os');
const { Client } = require('pg');

const app = express();
app.use(express.static('public'))
app.set('view engine', 'html');
app.engine('html', mustacheExpress());          // register file extension
app.set('views', __dirname);

app.get('/',function(req,res){
    res.status(200).send('Wild Animals of Massai Mara National Park');
});

app.get('/animal', async (req,res) => {
    const client = new Client({
        host: '127.0.0.1',
        user: 'db',
        password: 'dockerpass',
        database: 'pets',
        port: 5432,
    })
    console.log("Connecting to DB");
    await client.connect();
    console.log("Connected!");

    const imageId = getRandomInt(12) + 1;
    const sql = 'SELECT * FROM images WHERE imageid=' + imageId;
    console.log(`sql: ${sql}`);

    // res.render('index', {
    //     url: 'images/vulture.png',
    //     hostname: os.hostname()
    // });

    client.query(sql, (err, result) =>{
        if (err) {
            console.error(err);
            return;
        }
        console.log('DB queried');
        for (let row of res.rows) {
            console.log(row);
        }
            
        const url = result.rows[0].url;
        console.log(url);
        res.render('index', {
                url: url,
                hostname: os.hostname()
            });
        client.end();
    })
});

function getRandomInt(max) {
    return Math.floor(Math.random() * Math.floor(max));
}

app.listen(3000, '0.0.0.0');

console.log("Listening at 0.0.0.0:3000");