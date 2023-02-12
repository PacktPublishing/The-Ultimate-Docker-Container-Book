using Microsoft.AspNetCore.Mvc;
using PhotosApi.Data;
using PhotosApi.Models;

namespace PhotosApi.Controllers;

[ApiController]
[Route("")]
public partial class RacesController : ControllerBase
{
  private readonly PhotosDBContext context;

  public RacesController(PhotosDBContext context)
    {
    this.context = context;
  }

    [HttpGet("races")]
    public IEnumerable<RaceDto> GetRaces()
    {
      return context.Races
        .Select(r => new RaceDto(r.Id, r.SpeciesId, r.Name, r.Description))
        .ToList();
    }

    [HttpGet("species/{speciesId}/races")]
    public IEnumerable<RaceDto> GetRaces(int speciesId)
    {
      return context.Races
        .Select(r => new RaceDto(r.Id, r.SpeciesId, r.Name, r.Description))
        .Where(r => r.SpeciesId == speciesId)
        .ToList();
    }

    [HttpPost("species/{speciesId}/races")]
    public async Task Add(int speciesId, [FromBody]NameDescription name)
    {
      var max = context.Races.Max(r => r.Id);
      var newRace = new Race
      {
        Id = max+1, 
        SpeciesId = speciesId, 
        Name = name.Name,
        Description = name.Description
      };
      context.Races.Add(newRace);
      await context.SaveChangesAsync();
    }

    [HttpPut("species/{speciesId}/races/{raceId}")]
    public async Task<IActionResult> Update(int raceId, int speciesId, [FromBody]NameDescription name)
    {
      var race = context.Races  
        .SingleOrDefault(r => r.Id == raceId);
      if(race == null)
        return new NotFoundResult();
      race.Name = name.Name;
      race.Description = name.Description;
      await context.SaveChangesAsync();
      return new OkResult();
    }
}