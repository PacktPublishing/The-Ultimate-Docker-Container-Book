const express = require('express');
const app = express();
const port = 3000;

app.use((req, res, next) => {
    console.log(`[${new Date().toISOString()}] Incoming request: ${req.method} ${req.path}`);
    next();
});

app.get('/', (req, res) => {
    console.log('Handling request for the root path');
    res.send('Hello, World!');
});

app.get('/test', (req, res) => {
    console.log('Handling request for the /test path');
    res.send('This is the /test route')
});

app.use((req, res) => {
    console.log('Handle 404 not found');
    res.status(404).send('Not Found');
});

app.listen(port, '0.0.0.0', () => {
    console.log(`Server is running at http://0.0.0.0:${port}`);
});
