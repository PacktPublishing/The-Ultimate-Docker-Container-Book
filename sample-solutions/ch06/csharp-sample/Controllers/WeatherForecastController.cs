using Microsoft.AspNetCore.Mvc;

namespace csharp_sample.Controllers;

[ApiController]
[Route("[controller]")]
public class WeatherForecastController : ControllerBase
{
    private ILogger logger;
    public WeatherForecastController(ILogger<WeatherForecastController> logger)
    {
        this.logger = logger;
    }

    private static readonly string[] Summaries = new[]
    {
        "Freezing", "Bracing", "Chilly", "Cool", "Mild", "Warm", "Balmy", "Hot", "Sweltering", "Scorching"
    };

    [HttpGet(Name = "GetWeatherForecast")]
    public IEnumerable<WeatherForecast> Get()
    {
        logger.LogInformation("Accessing the /weatherforecast endpoint.");
        return Enumerable.Range(1, 20).Select(index => new WeatherForecast
        {
            Date = DateOnly.FromDateTime(DateTime.Now.AddDays(index)),
            TemperatureC = Random.Shared.Next(-20, 55),
            Summary = Summaries[Random.Shared.Next(Summaries.Length)]
        })
        .ToArray();
    }

    [HttpGet("/warning")]
    public string ShowWarning()
    {
        logger.LogWarning("This endpoint shows a warning!");
        return "Just a warning";
    } 
}
