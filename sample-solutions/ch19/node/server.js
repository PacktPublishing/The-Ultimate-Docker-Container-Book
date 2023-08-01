const app = require("express")();
const client = require("prom-client"); 

const register = client.register; 
const collectDefaultMetrics = client.collectDefaultMetrics; 
collectDefaultMetrics({ register });

const helloCounter = new client.Counter({ 
    name: 'my_hello_counter', 
    help: 'Counts the number of hello requests', 
}); 

app.get('/hello', (req, res) => {
    helloCounter.inc();
    const name = req.query.name || 'World';
    res.json({ message: `Hello, ${name}!` });
});

app.get('/metrics', async (req, res) => { 
    res.set('Content-Type', register.contentType); 
    res.end(await register.metrics()); 
}); 

app.listen(port = 3000, () => {
    console.log(`Example api is listening on http://localhost:3000`);
});