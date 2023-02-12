using Microsoft.EntityFrameworkCore;
using PhotosApi.Data;

var builder = WebApplication.CreateBuilder(args);
var devCorsPolicy = "devCorsPolicy";
builder.Services.AddCors(options =>
{
    options.AddPolicy(devCorsPolicy,
        policy =>
        {
            policy.AllowAnyOrigin()
                .AllowAnyHeader()
                .AllowAnyMethod();
        });
});

// Add services to the container.
builder.Services.AddDbContext<PhotosDBContext>(opt =>
{
    var connString = builder.Configuration.GetConnectionString("PhotosDbConnection");
    Console.WriteLine($"Connection String: {connString}");
    opt.UseNpgsql(connString)
        .UseSnakeCaseNamingConvention();
});

builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
    app.UseCors(devCorsPolicy);
}
// adjust in production!!!
app.UseCors(devCorsPolicy);

//app.UseHttpsRedirection();

app.UseAuthorization();

app.MapControllers();

app.Run();
