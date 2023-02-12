using Microsoft.AspNetCore.Mvc;
using PhotosApi.Data;
using PhotosApi.Models;

namespace PhotosApi.Controllers;

[ApiController]
[Route("[controller]")]
public class SpeciesController : ControllerBase
{
  public SpeciesController(PhotosDBContext context)
  {
    this.context = context;
  }

  private readonly PhotosDBContext context;

  [HttpGet]
    public IEnumerable<SpeciesDto> GetSpecies()
    {
      return context.Species
        .Select(s => new SpeciesDto(s.Id, s.Name, s.Description))
        .ToList();
    }

    [HttpPost]
    public async Task Add([FromBody]NameDescription name)
    {
      var max = context.Species.Max(r => r.Id);
      var newSpecies = new Species
      { 
        Id = max+1, 
        Name = name.Name, 
        Description = name.Description
      };
      context.Species.Add(newSpecies);
      await context.SaveChangesAsync();
    }

    [HttpPut("{speciesId}")]
    public async Task<IActionResult> Update(int speciesId, [FromBody]NameDescription name)
    {
      var spec = context.Species
        .SingleOrDefault(r => r.Id == speciesId);
      if(spec == null)
        return new NotFoundResult();
      spec.Name = name.Name; 
      spec.Description = name.Description;
      await context.SaveChangesAsync();
      return new OkResult();
    }
}
