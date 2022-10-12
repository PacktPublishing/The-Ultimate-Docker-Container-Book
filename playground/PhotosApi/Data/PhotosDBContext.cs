using Microsoft.EntityFrameworkCore;

namespace PhotosApi.Data;
public class PhotosDBContext : DbContext
{
  public PhotosDBContext(DbContextOptions<PhotosDBContext> options) 
    : base(options)
  { }

  public DbSet<Species> Species => Set<Species>(); 
  public DbSet<Race> Races => Set<Race>();
  public DbSet<Photo> Photos => Set<Photo>();
  public DbSet<Comment> Comments => Set<Comment>();
}
