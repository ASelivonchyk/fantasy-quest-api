![logo.png](dnd-logo.png)
<!--https://shields.io/badges -->
![JAVA](https://img.shields.io/badge/Java-21-green?style=for-the-badge&logo=Java)
![SPRING](https://img.shields.io/badge/Java-3.2.0-green?style=for-the-badge&logo=spring&label=Srping%20Boot)
![OpenAI](https://img.shields.io/badge/openai-GPT_3.5-%2306c498?style=for-the-badge&logo=openai&label=OpenAI)
![Docker](https://img.shields.io/badge/Docker-23-blue?style=for-the-badge&logo=docker&logoColor=blue)
![PostgresSQL](https://img.shields.io/badge/PostgreSQL-16-blue?style=for-the-badge&logo=PostgreSQL&logoColor=blue)
## <a name="what-is-this-api">About this API</a>
Fantasy DnD Quest API provides a way to play DnD game in single or multiplayer mode over HTTP requests.
There are 4 predefined playing race and characters, but player can create his own game character.
All in game content unique and provides by OpenAi, offering dynamic, personalized, and immersive content for players.
## <a name="available-roles">Particular qualities</a>
1. The API could dynamically generate quests based on user preferences, party composition, and previous in-game decisions.
   API can adapt story line to the party's compound, decisions and individual character skills.
2. AI-generated story lines create engaging and unpredictable quests with rich narratives, character dialogues and plot twists.  
   API could dynamically adjust the story based on player decisions and actions.
3. AI-generated quest seamlessly integrate with the overall narrative. Varied quest objectives, such as exploration, combat, diplomacy, and puzzle-solving, to cater to different player preferences.
4. Natural language processing for interactive dialogue systems, allowing players to engage in conversations with AI-driven NPCs.
   Dynamic responses that consider player choices and character backgrounds.
## <a name="available-roles"> Available races</a>
- **Human.** Humans are one of the most adaptable and widespread races in DnD. 
They don't have the special abilities of other races but are known for 
their versatility and ability to excel in various classes and roles.
- **Elf.** Elves are elegant and agile beings with a deep connection to nature.
They are divided into different subraces, such as High Elves, Wood Elves, 
and Drow (dark elves). Elves are often known for their keen senses, proficiency in archery, and longevity.
- **Orc.** Humanoid race in the DnD universe known for their strength, 
martial prowess, and often savage reputation. Orcs are typically larger 
and more robust than humans, with muscular builds and prominent tusks. 
They have a distinct, intimidating appearance.
## <a name="available-roles"> Available character classes</a>
- **Mag.** Spellcasters who gain their magical abilities through extensive study, 
research, and mastery of arcane arts. Wizards use spellbooks to record and prepare 
their spells, and they can cast spells from a diverse range 
of schools such as evocation, illusion, abjuration, and more.
- **Fighter.** Warriors who excel in physical combat, armed and unarmed. 
They are skilled with a wide range of weapons and armor, 
making them formidable adversaries on the battlefield. 
Fighters rely on their strength, agility, and combat training to overcome challenges
- **Archer.** Skilled outdoorsmen and marksmen who excel in archery and wilderness survival. 
Archers can have an affinity for nature and can track enemies proficiently.
Archer skilled trackers, expert marksmen, and have a unique ability to navigate and 
survive in various environments.
- **Thief.** Thief are stealthy and cunning characters known for their 
skills in subterfuge, precision strikes, and versatility. 
They are often thieves, spies, assassins, or adventurers with a knack 
for exploiting the weaknesses of their enemies.
## <a name="installation">Installation</a>
**To run this API, you will need to have on your machine:**
- Java version 21+
- PostgreSQL version 16
- Docker

**Configure application properties in `application.properties` file:**

 -- Database configuration -- 
<br>
- Create and run "data" database on port: 5432 with username "postgres" and password "user" in PostreSQL 
 <br>or 
- specify own in fields `spring.datasource.url`, `spring.datasource.username`,
  `spring.datasource.password`
<br>

-- Server HTTP port --
<br>
- By default, API open on 8085 port, but you can change port in field
  `server.port`
<br>

-- OpenAI --
<br>
- Create your own OpenAI API Key on `https://platform.openai.com/api-keys`
- fill it in field `spring.ai.openai.api-key`
<br>
## <a name="endpoints">API Endpoints</a>

| Method |             Endpoint             |                                                       Parameters                                                       |                Returned result                |                                Description                                 |
|:------:|:--------------------------------:|:----------------------------------------------------------------------------------------------------------------------:|:---------------------------------------------:|:--------------------------------------------------------------------------:|
| `POST` |       api/player/register        |                                                  "login", "password"                                                   |               none, Status 200                |                         register player in service                         |
| `POST` |         api/player/login         |                                                  "login", "password"                                                   |                jwt, Status 200                |                      player authentication in service                      |
| `POST` |          api/character           | "name", "playCharClass", "playCharRace", "strength", "dexterity", "constitution", "intelligence", "wisdom", "charisma" |               none, Status 201                |                            create new character                            |
| `GET`  |   api/character/{id}/inventory   |                                                  {id} - character id                                                   |               ["name", "count"]               |                   get information about character items                    |
| `PUT`  |   api/character/{id}/inventory   |                                   {id} - character id, "item", "count", "operation"                                    |               ["name", "count"]               | add or remove item from inventory by item name, "operation" type: add, sub |
| `GET`  |     api/adventure/available      |                                                          none                                                          |       ["serial", "title", description"]       |             receive 3 DnD Quest generated by AI to choose from             |
| `GET`  |   api/adventure/available/new    |                                                          none                                                          |       ["serial", "title", description"]       |          receive 3 more  DnD Quest generated by AI to choose from          |
| `GET`  | api/adventure/available/{serial} |                                              {serial} - adventure serial                                               | ["serial", "title", description", "stories"]  |                receive information about selected DnD Quest                |
