const express = require('express')
const app = express()
const hostname='0.0.0.0'
const port = 3000

app.get('/', (req, res) => res.send('Product Catalog'))

const bicycles = [
    { "id": 1, "name": "Mountanbike Driftwood 24\"", "unitPrice": 199 },
    { "id": 2, "name": "Tribal 100 Flat Bar Cycle Touring Road Bike", "unitPrice": 300 },
    { "id": 3, "name": "Siech Cycles Bike (58 cm)", "unitPrice": 459 }
]

app.get('/catalog', (req, res) => {
    res.send(bicycles);
})

app.listen(port, hostname, () => 
    console.log(`Product catalog listening on  ${hostname}:${port}!`))